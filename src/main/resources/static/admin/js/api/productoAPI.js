import { fetchApi } from '../utils/apiUtils.js';
 
export const ProductoAPI = {
    obtenerTodos: () => fetchApi('GET', '/producto'),
 
    obtenerPorId: (id) => fetchApi('GET', `/producto/${id}`),
 
    crear: (producto) => fetchApi('POST', '/producto', producto),
 
    actualizar: (id, producto) => fetchApi('PUT', `/producto/${id}`, producto),
 
    eliminar: (id) => fetchApi('DELETE', `/producto/${id}`)
};