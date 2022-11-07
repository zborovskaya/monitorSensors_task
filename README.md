# monitorSensors_task
Запуск программы 
Склонируйте репозиторий(git clone <url>).
Создайте базу данных в postgreSql с названием sensors_db (create database sensors_db).
В папке monitorSensors/sql находится sql скрипт для создания начальной структуры БД и инициализации данных. Запустите данный скрипт.
В папке resoursec в файле application.properties необходимо прописать свои spring.datasource.url (jdbc:postgresql://localhost:5432/sensors_db),spring.datasource.username (postgres), spring.datasource.password.
Запустите MonitorSensorsApplication (находится в папке by.zborovskaya.monitorSensors). В браузере введите http://localhost:8080/swagger-ui.html .
 
В sql скрипте инициализируется только админ. Для создания  user c ролью Viewer необходимо зарегистрироваться. Для этого нужно выбрать auth-controller метод /auth/registration. 
Без авторизации пользователь не может совершать никаких действий, кроме регистрации и логинизации. При выполнении метода регистрации или логинизации в ответе присылается json. Этот json необходимо скопировать. Затем нажать кнопку Authorize, затем в поле value скопированный json. 
  Если вы хотите зайти как администратор используйте пример json в auth/login. Остальные пользователи будут обычными user. 
  После авторизации user и admin могут просматривать список sensors, осуществлять поиск по имени и по названию модели, просматривать список имеющихся единиц измерения и типов. Администратор может также добавлять, редактировать и удалять sensor.
  !!! ДЛя смены роли необходимо сделать logout, и снова получить json (регистрация илипросто вход). И произвести авторизацию с новым json. 
  

  
  
