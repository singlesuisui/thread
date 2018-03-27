package com.sofia.semaphore;

public class Producer implements Runnable {
    // 每次生产的产品数量
    private int num;

    private String name;
    // 所在放置的仓库
    private Storage storage;

    private AwaitStorage awaitStorage;

    // 构造函数，设置仓库
    public Producer(Storage storage,String name) {
        this.storage = storage;
        this.name = name;
    }
    public Producer(AwaitStorage storage,String name) {
        this.awaitStorage = storage;
        this.name = name;
    }


    // 线程run函数
    public void run()
    {
//        while (true){
            produce(num);
//        }
    }

    // 调用仓库Storage的生产函数
    public void produce(int num)
    {
        awaitStorage.produce(num,name);
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