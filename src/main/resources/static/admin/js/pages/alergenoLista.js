import { crearNavbar } from '../componentes/navbar.js';
import { AlergenoAPI } from '../api/alergenoAPI.js';
 
document.addEventListener('DOMContentLoaded', async () => {
 
    // Inyectar navbar
    document.getElementById('navbar').innerHTML = crearNavbar();
 
    const root = document.getElementById('root');
 
    // Cargar categorias
    const alergenos = await AlergenoAPI.obtenerTodos();
 
    // Construir filas de la tabla
    let filas = '';
    for (const alergeno of alergenos) {
        filas += `
            <tr>
                <td>${alergeno.id}</td>
                <td>${alergeno.nombre}</td>
                <td>
                    <button><a href="alergenoDetalle.html?id=${alergeno.id}">Editar</a></button>
                    <button class="btn-borrar" onclick="eliminarAlergeno(${alergeno.id})">Borrar</button>
                </td>
            </tr>
        `;
    }
 
    // Inyectar contenido en root
    root.innerHTML = `
        <div class="contenedor">
            <h1>Alergenos</h1>
            <a href="alergenoDetalle.html">+ Nuevo alergenos</a>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    ${filas}
                </tbody>
            </table>
        </div>
    `;
});
 
// Funcion global para borrar
window.eliminarAlergeno = async (id) => {
    if (confirm('¿Seguro que quieres borrar este alergeno?')) {
        const response = await fetch(`http://localhost:8080/api/alergeno/${id}`, { method: 'DELETE' });
        if (response.ok) {
            location.reload();
        } else {
            alert('No se puede borrar este alergeno porque tiene productos asociados.');
        }
    }
};