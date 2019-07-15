package util;

public abstract class StaticValues {

    public static final String FILE_NAME = "Items.json";

    public abstract class Client{
        public static final String URL_SEARCH_ITEM = "https://api.mercadolibre.com/sites/MLA/search?q=";
        public static final String URL_CURRENCIES = "https://api.mercadolibre.com/currencies";
        public static final String NODE_RESULTS = "results";
    }

    public abstract class Resource{
        public static final String BASE_PATH = "/item";
        public static final String PARAM_QUERY = "query";
        public static final String PARAM_ORDER_CRITERIA = "ordenarpor";
        public static final String PARAM_ORDER_ORIENTATION = "ordenardeforma";
        public static final String PARAM_PRECIO_MIN = "preciominimo";
        public static final String PARAM_PRECIO_MAX = "preciomaximo";
        public static final String PARAM_TAG_FILTER = "tag";
        public static final String TAG_FILTER = "good_quality_thumbnail";
        public static final String PARAM_ID = ":id";
        public static final String TITLES_PATH = "/titulo";
    }

    public abstract class HTTP{
        public static final String ENCODING = "UTF-8";
        public static final String APPLICATION_JSON = "application/json";
        public static final String HEADER_ACCEPT = "Accept";
        public static final int CONFLICT = 409;
        public static final int NOT_FOUND = 404;
    }

    public abstract class ResponseMessage {
        // Mensajes de error
        public static final String ERROR_EDITAR_ITEM = "No se pudo editar el Item con id %1$s porque el mismo no existe.";
        public static final String ERROR_ELIMINAR_ITEM = "No se pudo eliminar el Item con id %1$s porque el mismo no existe.";
        public static final String ERROR_OBTENER_ITEM = "No se encontró el Item con id %1$s.";
        public static final String ERROR_BUSCAR_ITEM = "No se encontró ningún Item con la búsqueda por %1$s.";
        public static final String ERROR_SIN_DATOS = "No hay datos cargados de una búsqueda previa.";
        public static final String ERROR_CREAR_ITEM = "No se pudo crear el Item con id %1$s debido a que ya existe un item con dicho id.";

        // Mensajes de éxito
        public static final String ITEM_ELIMINADO = "Item id %1$s eliminado correctamente";
        public static final String ITEM_EXISTE = "El Item con el id %1$s existe";
        public static final String ITEM_NO_EXISTE = "El Item con el id %1$s no existe";
    }
}
