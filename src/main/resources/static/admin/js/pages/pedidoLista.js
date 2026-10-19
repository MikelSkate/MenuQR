import { crearNavbar } from '../componentes/navbar.js';
import { PedidoAPI } from '../api/pedidoAPI.js';
 
document.addEventListener('DOMContentLoaded', async () => {
 
    // Inyectar navbar
    document.getElementById('navbar').innerHTML = crearNavbar();
 
    const root = document.getElementById('root');
 
    // Cargar categorias
    const pedidos = await PedidoAPI.obtenerTodos();
 
    // Construir filas de la tabla
    let filas = '';
    for (const pedido of pedidos) {
            const fecha = new Date(pedido.fechaHora).toLocaleString('es-ES', {
            day: '2-digit',
            month: '2-digit',
            year: 'numeric',
            hour: '2-digit',
            minute: '2-digit'
        });
        filas += `
            <tr>
                <td>${pedido.id}</td>
                <td>${fecha}</td>
                <td>${pedido.entregado ? 'Entregado' : 'Pendiente'}</td>
                <td>${pedido.mesa.numero}</td>
                <td>
                    <button><a href="pedidoDetalle.html?id=${pedido.id}">Ver pedido</a></button>
                    <button class="btn-borrar" onclick="eliminarPedido(${pedido.id})">Borrar</button>
                </td>
            </tr>
        `;
    }
 
    // Inyectar contenido en root
    root.innerHTML = `
        <div class="contenedor">
            <h1>Pedidos</h1>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Fecha</th>
                        <th>Estado</th>
                        <th>Mesa</th>
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
window.eliminarPedido = async (id) => {
    if (confirm('¿Seguro que quieres borrar este pedido?')) {
        const response = await fetch(`http://localhost:8080/api/pedido/${id}`, { method: 'DELETE' });
        if (response.ok) {
            location.reload();
        } else {
            alert('No se puede borrar este pedido porque tiene líneas de pedido asociadas.');
        }
    }
};
