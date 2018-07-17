"RxBus2" 
=

This is an Rxbus2 library based on EventBus for Android. 
It simplifies the communication between Activities, Fragments etc.
 
Rxbus2 is pettern based on [Publish & Subscribe Pattern](https://en.wikipedia.org/wiki/Publish%E2%80%93subscribe_pattern) 

Advantages
- Loosely coupled, Easy to develop, Simply 

Disadvantages
- Debugging is difficult, Not easy management.


Different from other event buses
--
1. Use CompositeDisposable for efficient memory management.
2. You can receive events from the BaseClass. (BaseActivity, BaseFragment etc...)
3. You can register the same class using hascode.


Dependency
--
Gradle dependency:

Add it in your root build.gradle at the end of repositories:
<pre><code>
repositories {
    // ...
    maven { url 'https://jitpack.io' }
}
 </code></pre>
 
 Add the gradle dependency to your app module build.gradle file:
<pre><code>
dependencies {
    compile 'com.heinrichreimersoftware:material-drawer:2.3.3'
}
 </code></pre>
Get the latest dependency at jitpack.io.


RxBus2 in Step
--

1. Register and unregister your subscriber.

<pre><code>
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxBus.get().register(this);
    }
    @Override
     protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unResister(this);
     }
 </code></pre>
  
2. Post events. (Param Type : All of Obejct)
<pre><code>     
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send_left:
                RxBus.get().send(EventType.TAG.LEFT_MENU, new ArrayList<>());
                break;
            case R.id.btn_send_all:
                RxBus.get().send(EventType.TAG.ALL, "Send ALL");
                break;
            case R.id.btn_send_right:
                RxBus.get().send(EventType.TAG.RIGHT_MENU, 2018);
                break;
        }
    }
 </code></pre>  
     
        
3. Define a function (Subscribe) in the class that will receive the event.
        
 <pre><code>            
    @Subscribe(eventTag = EventType.TAG.MIDDLE)
    public void receiveMiddle(String receivedMsg) {
        mTextContent.append(receivedMsg + "\n");
    }

    @Subscribe(eventTag = EventType.TAG.ALL)
    public void receiveAll(String allMsg) {
        mTextContent.append(allMsg + "\n");
    }         
 </code></pre>  
 
4. You can also receive events in the base class.
If you need to receive events from the base class,
Use as follows.

<pre><code>   
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxBus.get().register(this, RxBusHelper.getRegistClass(this, BaseClass.class));
    }
    @Override
     protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unResister(this, RxBusHelper.getRegistClass(this, BaseClass.class));
     }
</code></pre>  




License
-
Copyright 2018 warrenth, Inc.

The contents of this repository are licensed under the [Apache-2.0](https://opensource.org/licenses/Apache-2.0)