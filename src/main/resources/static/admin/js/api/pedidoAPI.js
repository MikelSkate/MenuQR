import { fetchApi } from '../utils/apiUtils.js';
 
export const PedidoAPI = {
    obtenerTodos: () => fetchApi('GET', '/pedido'),
 
    obtenerPorId: (id) => fetchApi('GET', `/pedido/${id}`),
 
    crear: (pedido) => fetchApi('POST', '/pedido', pedido),
 
    actualizar: (id, pedido) => fetchApi('PUT', `/pedido/${id}`, pedido),
 
    eliminar: (id) => fetchApi('DELETE', `/pedido/${id}`)
};