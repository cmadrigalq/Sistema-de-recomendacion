package com.moviles1.cyn.ejemploscortos.Listeners;

import java.util.ArrayList;

public interface OnAdapterListener {
    void onProductoClicked(int position, String nombreProducto, int id);
    void calcularPrecioFinal(Double precio);

    void onDeleteProductClicked(int position, String title, int id);
}
