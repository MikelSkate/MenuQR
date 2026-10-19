import { crearNavbar } from '../componentes/navbar.js';
import { AlergenoAPI } from '../api/alergenoAPI.js';
 
document.addEventListener('DOMContentLoaded', async () => {
 
    // Inyectar navbar
    document.getElementById('navbar').innerHTML = crearNavbar();
 
    const root = document.getElementById('root');
 
    // Comprobar si estamos editando o creando
    const params = new URLSearchParams(window.location.search);
    const id = params.get('id');
 
    let alergeno = { nombre: '', linkImagen: '' };
    let titulo = 'Nuevo alergeno';
 
    if (id) {
        alergeno = await AlergenoAPI.obtenerPorId(id);
        titulo = 'Editar alergeno';
    }
 
    // Inyectar formulario en root
    root.innerHTML = `
        <div class="contenedor">
            <h1>${titulo}</h1>
            <form id="formulario">
                <div class="campo">
                    <label for="nombre">Nombre</label>
                    <input type="text" id="nombre" value="${alergeno.nombre}" required>
                    <label for="linkImagen">Link Imagen</label>
                    <input type="text" id="linkImagen" value="${alergeno.linkImagen}" required>
                </div>
                <button type="submit">${id ? 'Guardar cambios' : 'Crear alergeno'}</button>
                <a href="alergenos.html">Cancelar</a>
            </form>
        </div>
    `;
 
    // Manejar envio del formulario
    document.getElementById('formulario').addEventListener('submit', async (e) => {
        e.preventDefault();
 
        const datos = {
            nombre: document.getElementById('nombre').value,
            linkImagen: document.getElementById('linkImagen').value
        };
 
        if (id) {
            await AlergenoAPI.actualizar(id, datos);
        } else {
            await AlergenoAPI.crear(datos);
        }
 
        window.location.href = 'alergenos.html';
    });
});