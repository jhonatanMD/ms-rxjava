package com.ws.rx.controller;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AppController {

    //Observable 0 - n
    //Single 0 - 1
    //Maybe 0 - 1  -> valida si te trae información
    //Completable 0
    //Flowable 0 - n  -> valida si te trae información

    @GetMapping("/get")
    public Observable<Integer> getIntegers(){

        return Observable.just("1","2","2","2","2","3","4","5","6","67")
                .map(Integer::valueOf)
                //.filter(r -> r > 4)
                .distinct()
                .doOnNext(System.out::println);
    }


    @GetMapping("/getF")
    public Flowable<String> getIntegersF(){

        return get()
                .mergeWith(Flowable.empty())
                .switchIfEmpty(Flowable.just("no hay info"))
                .doOnNext(System.out::println);
    }


    Flowable<String> get(){
        return Flowable.empty();
    }

    @GetMapping("/getS")
    public Single<String> getSingle(){
        return Single.just("Hola amigos")
                .map(r -> "HOLA MUNDO")
                .flatMap(r -> getS());
    }

    @GetMapping("/getM")
    public Maybe<String> getMaybe(){
        return Maybe.just("Hola amigos")
                .map(r -> "HOLA MUNDO")
                .flatMap(r -> getM())
                .switchIfEmpty(Maybe.just("SABES QUE NO HAY INFO"));
    }

    @GetMapping("/getC")
    public Completable getCompletable(){

        return Completable.create(emitter -> System.out.println("PASO ALGO POR AQUI"));
    }

    Single<String> getS(){
        return Single.just("HOLA PLANETA");
    }

    Maybe<String> getM(){
        return Maybe.empty();
    }
}
