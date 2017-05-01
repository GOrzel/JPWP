package watek;

/**
 * Created by Administrator on 2016-12-01.
 */
public interface Kontrolowany {
    void subscribe(Listener listener);
    void przelacz(boolean tryb);
}
