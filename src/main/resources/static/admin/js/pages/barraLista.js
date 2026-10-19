import { crearNavbar } from '../componentes/navbar.js';

const API_URL = 'http://localhost:8080/api';

document.addEventListener('DOMContentLoaded', async () => {

    document.getElementById('navbar').innerHTML = crearNavbar();

    const root = document.getElementById('root');

    const lineas = await fetch(`${API_URL}/lineapedido/destino/BAR/listo/false`)
        .then(res => res.json());

    let filas = '';
    for (const linea of lineas) {
        filas += `
            <tr>
                <td>${linea.pedido.mesa.numero}</td>
                <td>${linea.producto.nombre}</td>
                <td>${linea.cantidad}</td>
                <td>
                    <button class="btn-listo" onclick="marcarListo(${linea.id})">Marcar listo</button>
                </td>
            </tr>
        `;
    }

    root.innerHTML = `
        <div class="contenedor">
            <h1>Cocina</h1>
            ${lineas.length === 0 ? '<p>No hay pedidos pendientes</p>' : `
            <table>
                <thead>
                    <tr>
                        <th>Mesa</th>
                        <th>Producto</th>
                        <th>Cantidad</th>
                        <th>Acción</th>
                    </tr>
                </thead>
                <tbody>
                    ${filas}
                </tbody>
            </table>`}
        </div>
    `;
});

window.marcarListo = async (id) => {
    await fetch(`${API_URL}/lineapedido/${id}/listo`, {
        method: 'PATCH'
    });
    location.reload();
};