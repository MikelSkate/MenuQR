import { fetchApi } from '../utils/apiUtils.js';

export const PedidoAPI = {
    crear: (pedido) => fetchApi('POST', '/pedido', pedido)
};

export const LineaPedidoAPI = {
    crear: (lineaPedido) => fetchApi('POST', '/lineapedido', lineaPedido)
};

export const MesaAPI = {
    obtenerPorNumero: (numero) => fetchApi('GET', `/mesa/numero/${numero}`)
};