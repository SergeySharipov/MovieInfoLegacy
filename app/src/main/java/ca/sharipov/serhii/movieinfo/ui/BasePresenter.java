package ca.sharipov.serhii.movieinfo.ui;

public interface BasePresenter<V> {

    void takeView(V v);

    void dropView();
}

