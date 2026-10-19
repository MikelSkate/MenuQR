import { CategoriaAPI } from '../api/categoriaAPI.js';
import { ProductoAPI } from '../api/productoAPI.js';
import { PedidoAPI, LineaPedidoAPI, MesaAPI } from '../api/pedidoAPI.js';
import { crearFooter } from '../componentes/footer.js';

// ===== ESTADO DEL CARRITO =====
let carrito = JSON.parse(localStorage.getItem('carrito')) || [];
let productosCache = [];

const guardarCarrito = () => {
    localStorage.setItem('carrito', JSON.stringify(carrito));
};

// ===== OPERACIONES DEL CARRITO =====
const anadirAlCarrito = (productoId) => {
    const producto = productosCache.find(p => p.id === productoId);
    if (!producto) return;

    const item = carrito.find(i => i.producto.id === productoId);
    if (item) {
        item.cantidad++;
    } else {
        carrito.push({ producto, cantidad: 1 });
    }
    guardarCarrito();
    renderCarrito();
};

const cambiarCantidad = (productoId, delta) => {
    const item = carrito.find(i => i.producto.id === productoId);
    if (!item) return;

    item.cantidad += delta;
    if (item.cantidad <= 0) {
        carrito = carrito.filter(i => i.producto.id !== productoId);
    }
    guardarCarrito();
    renderCarrito();
};

const vaciarCarrito = () => {
    carrito = [];
    guardarCarrito();
    renderCarrito();
};

const calcularTotal = () => {
    return carrito.reduce((sum, i) => sum + i.producto.precio * i.cantidad, 0);
};

// ===== RENDER DEL CARRITO =====
const renderCarrito = () => {
    const contenedor = document.getElementById('carrito');

    if (carrito.length === 0) {
        contenedor.innerHTML = `<p class="carrito-vacio">Tu pedido está vacío</p>`;
        return;
    }

    let lineas = '';
    for (const item of carrito) {
        lineas += `
            <div class="carrito-linea">
                <span class="carrito-nombre">${item.producto.nombre}</span>
                <div class="carrito-controles">
                    <button class="btn-cantidad" data-id="${item.producto.id}" data-delta="-1">−</button>
                    <span class="carrito-cantidad">${item.cantidad}</span>
                    <button class="btn-cantidad" data-id="${item.producto.id}" data-delta="1">+</button>
                </div>
                <span class="carrito-subtotal">${(item.producto.precio * item.cantidad).toFixed(2)} €</span>
            </div>
        `;
    }

    contenedor.innerHTML = `
        <h2 class="carrito-titulo">Tu pedido</h2>
        <div class="carrito-lineas">${lineas}</div>
        <div class="carrito-total">
            <span>Total</span>
            <span>${calcularTotal().toFixed(2)} €</span>
        </div>
        <button id="btn-confirmar" class="btn-confirmar">Confirmar pedido</button>
        <button id="btn-vaciar" class="btn-vaciar">Vaciar</button>
    `;
};

// ===== ENVÍO DEL PEDIDO =====
const confirmarPedido = async () => {
    if (carrito.length === 0) return;

    try {
        const params = new URLSearchParams(window.location.search);
        const numeroMesa = params.get('mesa') || '0';
        const mesa = await MesaAPI.obtenerPorNumero(numeroMesa);
        const ahora = new Date();
        const fechaHora = new Date(ahora.getTime() - ahora.getTimezoneOffset() * 60000).toISOString().slice(0, 19);

        const pedido = await PedidoAPI.crear({
            fechaHora: fechaHora,
            mesa: { id: mesa.id }
        });

        for (const item of carrito) {
            await LineaPedidoAPI.crear({
                cantidad: item.cantidad,
                precioUnitario: item.producto.precio,
                listo: false,
                producto: { id: item.producto.id },
                pedido: { id: pedido.id }
            });
        }

        vaciarCarrito();
        alert('Pedido enviado correctamente');
    } catch (err) {
        console.error(err);
        alert('Error al enviar el pedido');
    }
};

// ===== INIT =====
document.addEventListener('DOMContentLoaded', async () => {
    const root = document.getElementById('root');

    const categorias = await CategoriaAPI.obtenerTodos();
    productosCache = await ProductoAPI.obtenerTodos();

    let secciones = '';
    for (const cat of categorias) {
        const productosCat = productosCache.filter(p => p.categoria && p.categoria.id === cat.id);
        if (productosCat.length === 0) continue;

        let cards = '';
        for (const p of productosCat) {
            // Construye los emojis de alergenos (solo si tiene alguno)
            const alergenosHtml = (p.alergenos && p.alergenos.length > 0)
                ? `<div class="producto-alergenos">
                    ${p.alergenos.map(a => `<span class="alergeno" title="${a.nombre}">${a.linkImagen}</span>`).join('')}
                </div>`
                : '';

            cards += `
                <div class="producto-card">
                    <img class="producto-img" src="${p.linkImagen}" alt="${p.nombre}">
                    <h3 class="producto-nombre">${p.nombre}</h3>
                    <p class="producto-desc">${p.descripcion || ''}</p>
                    ${alergenosHtml}
                    <div class="producto-pie">
                        <span class="producto-precio">${p.precio.toFixed(2)} €</span>
                        <button class="btn-anadir" data-id="${p.id}">+</button>
                    </div>
                </div>
            `;
        }

        secciones += `
            <section class="categoria-seccion">
                <h2 class="categoria-titulo">${cat.nombre}</h2>
                <div class="productos-grid">${cards}</div>
            </section>
        `;
    }
    root.innerHTML = secciones;

    renderCarrito();

    document.addEventListener('click', (e) => {
        if (e.target.matches('.btn-anadir')) {
            const id = Number(e.target.dataset.id);
            anadirAlCarrito(id);
        }
        if (e.target.matches('.btn-cantidad')) {
            const id = Number(e.target.dataset.id);
            const delta = Number(e.target.dataset.delta);
            cambiarCantidad(id, delta);
        }
        if (e.target.id === 'btn-confirmar') {
            confirmarPedido();
        }
        if (e.target.id === 'btn-vaciar') {
            vaciarCarrito();
        }
    });

    document.getElementById('footer').innerHTML = crearFooter();
});
