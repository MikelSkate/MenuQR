import { crearNavbar } from '../componentes/navbar.js';
import { MesaAPI } from '../api/mesaAPI.js';
 
document.addEventListener('DOMContentLoaded', async () => {
 
    // Inyectar navbar
    document.getElementById('navbar').innerHTML = crearNavbar();
 
    const root = document.getElementById('root');
 
    // Cargar categorias
    const mesas = await MesaAPI.obtenerTodos();
 
    // Construir filas de la tabla
    let filas = '';
    for (const mesa of mesas) {
        filas += `
            <tr>
                <td>${mesa.id}</td>
                <td>${mesa.numero}</td>
                <td>
                    <button><a href="mesaDetalle.html?id=${mesa.id}">Editar</a></button>
                    <button class="btn-borrar" onclick="eliminarMesa(${mesa.id})">Borrar</button>
                </td>
            </tr>
        `;
    }
 
    // Inyectar contenido en root
    root.innerHTML = `
        <div class="contenedor">
            <h1>Mesas</h1>
            <a href="mesaDetalle.html">+ Nueva mesa</a>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Numero de mesa</th>
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
window.eliminarMesa = async (id) => {
    if (confirm('¿Seguro que quieres borrar esta mesa?')) {
        const response = await fetch(`http://localhost:8080/api/mesa/${id}`, { method: 'DELETE' });
        if (response.ok) {
            location.reload();
        } else {
            alert('No se puede borrar esta mesa porque tiene pedidos asociados.');
        }
    }
};