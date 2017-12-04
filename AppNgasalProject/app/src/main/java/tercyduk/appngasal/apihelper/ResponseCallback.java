package tercyduk.appngasal.apihelper;

import tercyduk.appngasal.modules.auth.user.Response;

/**
 * Created by davidbezalellaoli on 11/8/17.
 */

public interface ResponseCallback {
    public void success(Response response);
    public void fail(String message);
    public void internalServerError(int message);
}
