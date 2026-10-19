import { crearNavbar } from '../componentes/navbar.js';
import { ProductoAPI } from '../api/productoAPI.js';
import { CategoriaAPI } from '../api/categoriaAPI.js';
import { AlergenoAPI } from '../api/alergenoAPI.js';

document.addEventListener('DOMContentLoaded', async () => {

    // Inyectar navbar
    document.getElementById('navbar').innerHTML = crearNavbar();

    const root = document.getElementById('root');

    // Carga las categorias y alergenos existentes
    const categorias = await CategoriaAPI.obtenerTodos();
    const alergenos = await AlergenoAPI.obtenerTodos();

    // Comprobar si estamos editando o creando
    const params = new URLSearchParams(window.location.search);
    const id = params.get('id');

    let producto = { nombre: '', precio: '', linkImagen: '', descripcion: '', destino: '', alergenos: [] };
    let titulo = 'Nuevo producto';

    if (id) {
        producto = await ProductoAPI.obtenerPorId(id);
        titulo = 'Editar producto';
    }

    // Hace una lista de categorias
    let opciones = '';
    for (const categoria of categorias) {
        const seleccionada = producto.categoria && producto.categoria.id == categoria.id ? 'selected' : '';
        opciones += `<option value="${categoria.id}" ${seleccionada}>${categoria.nombre}</option>`;
    }

    // Hace los checkboxes de alergenos
    let checkboxesAlergenos = '';
    for (const alergeno of alergenos) {
        const marcado = producto.alergenos && producto.alergenos.some(a => a.id === alergeno.id) ? 'checked' : '';
        checkboxesAlergenos += `
            <label class="checkbox-alergeno">
                <input type="checkbox" value="${alergeno.id}" ${marcado}>
                ${alergeno.linkImagen} ${alergeno.nombre}
            </label>
        `;
    }

    // Inyectar formulario en root
    root.innerHTML = `
        <div class="contenedor">
            <h1>${titulo}</h1>
            <form id="formulario">
                <div class="campo">
                    <label for="nombre">Nombre</label>
                    <input type="text" id="nombre" value="${producto.nombre}" required>

                    <label for="precio">Precio</label>
                    <input type="number" id="precio" step="0.01" value="${producto.precio}" required>

                    <label for="linkImagen">Link imagen</label>
                    <input type="text" id="linkImagen" value="${producto.linkImagen}" required>

                    <label for="descripcion">Descripcion</label>
                    <input type="text" id="descripcion" value="${producto.descripcion}" required>

                    <label for="destino">Destino</label>
                    <select id="destino" required>
                        <option value="BAR" ${producto.destino === 'BAR' ? 'selected' : ''}>Bar</option>
                        <option value="COCINA" ${producto.destino === 'COCINA' ? 'selected' : ''}>Cocina</option>
                    </select>

                    <label for="categoria">Categoría</label>
                    <select id="categoria" required>
                        ${opciones}
                    </select>

                    <label>Alérgenos</label>
                    <div id="lista-alergenos">
                        ${checkboxesAlergenos}
                    </div>
                </div>
                <button type="submit">${id ? 'Guardar cambios' : 'Crear producto'}</button>
                <a href="productos.html">Cancelar</a>
            </form>
        </div>
    `;

    // Manejar envio del formulario
    document.getElementById('formulario').addEventListener('submit', async (e) => {
        e.preventDefault();

        // Recoge los alergenos marcados como array de objetos { id }
        const alergenosSeleccionados = [...document.querySelectorAll('#lista-alergenos input:checked')]
            .map(input => ({ id: Number(input.value) }));

        const datos = {
            nombre: document.getElementById('nombre').value,
            precio: document.getElementById('precio').value,
            linkImagen: document.getElementById('linkImagen').value,
            descripcion: document.getElementById('descripcion').value,
            destino: document.getElementById('destino').value,
            categoria: { id: document.getElementById('categoria').value },
            alergenos: alergenosSeleccionados
        };

        if (id) {
            await ProductoAPI.actualizar(id, datos);
        } else {
            await ProductoAPI.crear(datos);
        }

        window.location.href = 'productos.html';
    });
});