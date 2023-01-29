# LastifyFM
This project follows a combination of 2 componnents. One is the clean architecture and the other is the MVI(Model-View-Intent) flow


# What is MVI ?
MVI Stands for Model View Intent. The architecture is inspired by the unidirectional control flow.
The role of each MVI component is as follows:
* **Model** : Model in MVI represents a state. For example UI might have different states like Data Loading, Error, Update UI etc. Each State is stored as similar to the object in the model. This component interacts with beneath layers to access data.
* **View** : View represents our UI layer. View is nothing but an interface that is implemented by our Activities/Fragments. This interface is nothing but a container that accepts model and renders it as UI.
* **Intent** : Intent represents an intent or a desire to perform an action, either by the user or the app itself. 


### How MVI works
For every action, **View** receives an **Intent**. The **Presenter(ViewModel)** observes the **Intent**. Then pass it on to respective domain layer to process same to give back **Model**. The resultant **Model** is translated to a state which is received by the **View**. 
