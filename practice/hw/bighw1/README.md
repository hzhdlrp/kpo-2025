# Запуск

Для запуска приложения необходимо запустить функцию src.main.java.service.Main.main

# Команды


Доступные команды:

- create
    - account \<name\> 
        - создает новый аккаунт с именем \<name\>
    - category \<name\> \<type\> (доступные значения \<type\>: "income", "expense")
        - создает новую категорию с названием \<name\> и типом \<type\>
    - operation \<account_name\> \<category_name\> \<amount\>
        - создает операцию для аккаунта с именем \<account_name\> в категории с названием \<category_name\> и суммой \<amount\> 
        - (если тип категории "income", то сумма должна быть положительной, а если "expense" - то отрицательной)
- delete
    - account \<name\>
        - удаляет аккаунт с именем \<name\>
    - operation \<id\>
        - удаляет операцию с id == \<id\>
    - category \<name\>
        - удаляет категорию с именем \<name\>
- change
    - account name \<from\> \<to\>
            - изменяет имя аккаунта с \<from\> на \<to\>
- end
    - завершает работу

