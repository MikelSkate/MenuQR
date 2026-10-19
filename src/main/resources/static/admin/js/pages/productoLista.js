import { crearNavbar } from '../componentes/navbar.js';
import { ProductoAPI } from '../api/productoAPI.js';
 
document.addEventListener('DOMContentLoaded', async () => {
 
    // Inyectar navbar
    document.getElementById('navbar').innerHTML = crearNavbar();
 
    const root = document.getElementById('root');
 
    // Cargar categorias
    const productos = await ProductoAPI.obtenerTodos();
 
    // Construir filas de la tabla
    let filas = '';
    for (const producto of productos) {
        filas += `
            <tr>
                <td>${producto.id}</td>
                <td>${producto.nombre}</td>
                <td>${producto.precio}</td>
                <td>${producto.destino}</td>
                <td>${producto.categoria.nombre}</td>
                <td>
                    <button><a href="productoDetalle.html?id=${producto.id}">Editar</a></button>
                    <button class="btn-borrar" onclick="eliminarProducto(${producto.id})">Borrar</button>
                </td>
            </tr>
        `;
    }
 
    // Inyectar contenido en root
    root.innerHTML = `
        <div class="contenedor">
            <h1>Productos</h1>
            <a href="productoDetalle.html">+ Nuevo producto</a>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Precio</th>
                        <th>Destino</th>
                        <th>Categoria</th>
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
 
// Funcion para borrar
window.eliminarProducto = async (id) => {
    if (confirm('¿Seguro que quieres borrar este producto?')) {
        const response = await fetch(`http://localhost:8080/api/producto/${id}`, { method: 'DELETE' });
        if (response.ok) {
            location.reload();
        } else {
            alert('No se puede borrar este producto porque tiene pedidos asociados.');
        }
    }
};
