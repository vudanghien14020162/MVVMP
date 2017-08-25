package demo.exercise.pclap.demomvvpp.screen;

/**
 * Created by PC Lap on 8/25/2017.
 */
public interface BaseViewModel<T extends BasePresenter>{

    void onStart();

    void onStop();

    void setPresenter(T presenter);

}
