package com.example.rxjavaexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single

import io.reactivex.schedulers.Schedulers
import java.util.*

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val observable = Observable.just(1,2,3); // создаем источник данных
        val single = Single.just(1); //может быть толко 1 результат
        val flowable = Flowable.just(1,2,3);

        val btn: Button = findViewById(R.id.button)

        btn.setOnClickListener {
            Log.e(TAG, "click")
        }

        val dispose = dataSource().subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.newThread())
            .subscribe({
                btn.text = "Next in  $it"
                Log.e(TAG, "next int $it")
            },{

            },{

            })

    }

    fun dataSource(): Observable<Int>{
        return Observable.create{ subscriber ->
            for (i in 1..1000){
                Thread.sleep(500)
                subscriber.onNext(i)
            }

        }
    }

}