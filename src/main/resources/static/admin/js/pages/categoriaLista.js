import { crearNavbar } from '../componentes/navbar.js';
import { CategoriaAPI } from '../api/categoriaAPI.js';
 
document.addEventListener('DOMContentLoaded', async () => {
 
    // Inyectar navbar
    document.getElementById('navbar').innerHTML = crearNavbar();
 
    const root = document.getElementById('root');
 
    // Cargar categorias
    const categorias = await CategoriaAPI.obtenerTodos();
 
    // Construir filas de la tabla
    let filas = '';
    for (const categoria of categorias) {
        filas += `
            <tr>
                <td>${categoria.id}</td>
                <td>${categoria.nombre}</td>
                <td>
                    <button><a href="categoriaDetalle.html?id=${categoria.id}">Editar</a></button>
                    <button class="btn-borrar" onclick="eliminarCategoria(${categoria.id})">Borrar</button>
                </td>
            </tr>
        `;
    }
 
    // Inyectar contenido en root
    root.innerHTML = `
        <div class="contenedor">
            <h1>Categorías</h1>
            <a href="categoriaDetalle.html">+ Nueva categoría</a>
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
window.eliminarCategoria = async (id) => {
    if (confirm('¿Seguro que quieres borrar esta categoría?')) {
        const response = await fetch(`http://localhost:8080/api/categoria/${id}`, { method: 'DELETE' });
        if (response.ok) {
            location.reload();
        } else {
            alert('No se puede borrar esta categoría porque tiene productos asociados.');
        }
    }
};