import { crearNavbar } from '../componentes/navbar.js';
import { PedidoAPI } from '../api/pedidoAPI.js';

document.addEventListener('DOMContentLoaded', async () => {

    document.getElementById('navbar').innerHTML = crearNavbar();

    const root = document.getElementById('root');

    const params = new URLSearchParams(window.location.search);
    const id = params.get('id');

    const pedido = await PedidoAPI.obtenerPorId(id);

    console.log(pedido);

    const fecha = new Date(pedido.fechaHora).toLocaleString('es-ES', {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric',
        hour: '2-digit',
        minute: '2-digit'
    });

    let filas = '';
    for (const linea of pedido.lineas) {
        const subtotal = (linea.cantidad * linea.precioUnitario).toFixed(2);
        filas += `
            <tr>
                <td>${linea.producto.nombre}</td>
                <td>${linea.listo ? 'Entregado' : 'Pendiente'}</td>
                <td>${linea.cantidad}</td>
                <td>${linea.precioUnitario} €</td>
                <td>${subtotal} €</td>
            </tr>
        `;
    }

    root.innerHTML = `
        <div class="contenedor">
            <h1>Pedido #${pedido.id}</h1>
            <p>Mesa: ${pedido.mesa ? pedido.mesa.numero : 'Sin mesa'}</p>
            <p>Fecha: ${fecha}</p>
            <p>Estado: ${pedido.entregado ? 'Entregado' : 'Pendiente'}</p>

            <table>
                <thead>
                    <tr>
                        <th>Producto</th>
                        <th>Listo</th>
                        <th>Cantidad</th>
                        <th>Precio unitario</th>
                        <th>Subtotal</th>
                    </tr>
                </thead>
                <tbody>
                    ${filas}
                </tbody>
            </table>
            <a href="pedidos.html" class="btn-volver">Volver</a>
        </div>
    `;
});