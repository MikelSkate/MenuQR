import { crearNavbar } from '../componentes/navbar.js';
import { CategoriaAPI } from '../api/categoriaAPI.js';
 
document.addEventListener('DOMContentLoaded', async () => {
 
    // Inyectar navbar
    document.getElementById('navbar').innerHTML = crearNavbar();
 
    const root = document.getElementById('root');
 
    // Comprobar si estamos editando o creando
    const params = new URLSearchParams(window.location.search);
    const id = params.get('id');
 
    let categoria = { nombre: '' };
    let titulo = 'Nueva categoría';
 
    if (id) {
        categoria = await CategoriaAPI.obtenerPorId(id);
        titulo = 'Editar categoría';
    }
 
    // Inyectar formulario en root
    root.innerHTML = `
        <div class="contenedor">
            <h1>${titulo}</h1>
            <form id="formulario">
                <div class="campo">
                    <label for="nombre">Nombre</label>
                    <input type="text" id="nombre" value="${categoria.nombre}" required>
                </div>
                <button type="submit">${id ? 'Guardar cambios' : 'Crear categoría'}</button>
                <a href="categorias.html">Cancelar</a>
            </form>
        </div>
    `;
 
    // Manejar envio del formulario
    document.getElementById('formulario').addEventListener('submit', async (e) => {
        e.preventDefault();
 
        const datos = {
            nombre: document.getElementById('nombre').value
        };
 
        if (id) {
            await CategoriaAPI.actualizar(id, datos);
        } else {
            await CategoriaAPI.crear(datos);
        }
 
        window.location.href = 'categorias.html';
    });
});