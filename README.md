"RxBus2" 
=

This is an Rxbus2 library based on EventBus for Android. 
It simplifies the communication between Activities, Fragments etc.
 
Rxbus2 is pettern based on [Publish & Subscribe Pattern](https://en.wikipedia.org/wiki/Publish%E2%80%93subscribe_pattern) 


Advantages
- Loosely coupled, Easy to develop, Simply 

Disadvantages
- Debugging is difficult, Not easy management.

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


License
-
Copyright 2018 warrenth, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.