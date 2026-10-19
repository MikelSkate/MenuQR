import { fetchApi } from '../utils/apiUtils.js';
 
export const ProductoAPI = {
    obtenerTodos: () => fetchApi('GET', '/producto')
};