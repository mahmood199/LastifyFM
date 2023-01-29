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


### Architecture - MVI + Clean Architecture
This project follows the clean architecture which implies that each components in the archirecture points in one direction. That means child classes don't have refernce to their parent classes. The app cna be divided into 3 major areas for handling of data. They are - 

- Presentation/App: Consists of Activities, Fragments and ViewModels. This layer interacts with UI. 
- Domain: Contains the business logic of the application. This is the individual and innermost circle of clean Architecture.
- Data: Consists of Repositories. This layer would implement interface exposed by domain layer and dispenses data to the app.

![image](https://user-images.githubusercontent.com/58071934/215340081-9baa2020-e64d-42e2-84d4-c8fa42e81a20.png)



### Diagramatic representation of Dataflow

![image](https://user-images.githubusercontent.com/58071934/215340662-c22a93da-7799-46d5-a2d6-e68e320df4d1.png)


### App screens
- All tags/genres screen - The screen which show the user the list of genres which user can click to see its details. User can expand the list to see more tags \

![WhatsApp Image 2023-01-29 at 6 43 01 PM (1)](https://user-images.githubusercontent.com/58071934/215340919-abcc2a6c-f641-40af-9b5b-a5f6f9dce1e7.jpeg)
![WhatsApp Image 2023-01-29 at 6 43 01 PM](https://user-images.githubusercontent.com/58071934/215340920-e5900542-4199-4fbd-8ad9-e63dbcad0fb2.jpeg)

- Then we have a genre detail screen where user can see the genre detail such as artist, albums and tracks related to that genre

![WhatsApp Image 2023-01-29 at 6 43 02 PM (2)](https://user-images.githubusercontent.com/58071934/215341004-26f8e121-100d-4d0b-90a6-c1a6302aff2c.jpeg)
![WhatsApp Image 2023-01-29 at 6 43 02 PM (1)](https://user-images.githubusercontent.com/58071934/215341008-a72ec448-5506-489d-a96a-a890f1b61db4.jpeg)
![WhatsApp Image 2023-01-29 at 6 43 02 PM](https://user-images.githubusercontent.com/58071934/215341009-7ef8368a-66bf-4605-a635-94217d4d67a7.jpeg)


- Clicking on albums, artist or tracks leads to details of that screen.
### Note - This navigation is done only for artist and not for album and tracks as those are very similar behavior and implementation

![WhatsApp Image 2023-01-29 at 6 43 03 PM](https://user-images.githubusercontent.com/58071934/215341100-9117f53b-5e7d-4347-be09-4458625cae2f.jpeg)


