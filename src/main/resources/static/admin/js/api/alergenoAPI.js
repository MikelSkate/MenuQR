import { fetchApi } from '../utils/apiUtils.js';
 
export const AlergenoAPI = {
    obtenerTodos: () => fetchApi('GET', '/alergeno'),
 
    obtenerPorId: (id) => fetchApi('GET', `/alergeno/${id}`),
 
    crear: (alergeno) => fetchApi('POST', '/alergeno', alergeno),
 
    actualizar: (id, alergeno) => fetchApi('PUT', `/alergeno/${id}`, alergeno),
 
    eliminar: (id) => fetchApi('DELETE', `/alergeno/${id}`)
};