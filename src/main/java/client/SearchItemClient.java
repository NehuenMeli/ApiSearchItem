package client;

import model.Currency;
import model.Item;

import java.util.Collection;

/**
 * Interfaz Cliente responsable de llevar a cabo la obtención de los datos para conformar un {@link Item}.
 *
 */
public interface SearchItemClient {

    /**
     * Realiza una busqueda de todos aquellos {@link Item} en base al parametro de entrada
     *
     * @param searchQuery cadena de búsqueda utilizada para filtrar los {@link Item} a obtener
     * @return Con los {@link Item} obtenidos de un origen definido
     */
    public Collection<Item> getItems(String searchQuery);

    /**
     * Método de soporte para la creación de un {@link Item}, definiendo la {@link Currency} relacionada al mismo
     *
     * @param currencyId id utilizado para identificar unívocamente la {@link Currency}
     * @return Con la {@link Currency} que corresponde al id de entrada
     */
    public Currency getCurrency(String currencyId);

    /**
     * Obtiene el listado de {@link Currency} procedente del origen de datos definido para poder trabajar con ellas luego
     *
     * @return grupo de todas las {@link Currency} definidas en el origen de datos
     */
    public Currency[] getCurrencies();
}
