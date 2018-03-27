package com.sofia.semaphore;

/**
 * Created by yingbo.gu on 2018-03-26.
 */
public class Consumer implements Runnable {
    // 每次消费的产品数量
    private int num;
    private String name;
    // 所在放置的仓库
    private Storage storage;

    private AwaitStorage awaitStorage;

    // 构造函数，设置仓库
    public Consumer(Storage storage,String name) {
        this.storage = storage;
        this.name  = name;
    }
    // 构造函数，设置仓库
    public Consumer(AwaitStorage storage,String name) {
        this.awaitStorage = storage;
        this.name  = name;
    }

    // 线程run函数
    public void run()
    {
//        while (true){
            consume(num);
//        }
    }

    // 调用仓库Storage的生产函数
    public void consume(int num)
    {
        awaitStorage.consume(num,name);
    }

    // get/set方法
    public int getNum()
    {
        return num;
    }

    public void setNum(int num)
    {
        this.num = num;
    }

    public Storage getStorage()
    {
        return storage;
    }

    public void setStorage(Storage storage)
    {
        this.storage = storage;
    }
}
