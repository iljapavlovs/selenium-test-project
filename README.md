# selenium-test-project

### Homework Description
In order to complete this homework you will need to
1. Create a fork from my repo (https://guides.github.com/activities/forking/) - this will create a copy of this project in your GitHub account
2. Complete Task 1
3. Create a Pull Request for my original project (https://help.github.com/articles/creating-a-pull-request-from-a-fork/) - this will enable to easily review your code in GitHub

### Task 1:
1. Open a browser and maximize it.
2. Go to ss.com
3. Change language to russian if needed
4. Search for on a Search page 
5. Choose different search criterias
6. Press "Search" button
7. Verify that at least one advertisment is displyed

---
### Task 2:
8. Sort search results by price and select "продажа" option from the dropdown
9. Go to advanced search
10. Set the price from 0 to 300 as a search parameter 
11. Select at least 3 any advertisments 
12. Press "Show selected advertisments" button
13. Check that the ads on the page match the selected previously.

**NOTE** - 
1. Think about - how to make the tests Small, Atomic (One test - one clear goal, TC should verify only one functionality) and Autonomous. In order to do that, examine
 * how many features are tested
 * can or should those features be divided into separate test
2. Try to use CSS Selectors instead of XPath
---

### How this template was created
This template was generated with following command:
```
mvn archetype:generate -DgroupId=com.mycompany.selenium -DartifactId=selenium-test-project -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```
Afterwards, following was added:
* Selenium and TestNg dependencies into pom.xml
* .gitignore file for not git tracking unnecessary files, which we don\`t want to commit
* Test resources folder into _src/test_. Also marked as "Test Resources" in IDEA
* Added drivers into _src/test/resources_ folder. **NOTE** - it might be needed to update the driver versions


---
##### Task 1:
1. Открыть браузер и развернуть на весь экран.
2. Зайти на ss.com.
3. Поменять язык на русский.
4. в поиске ввести искомую фразу (напр. Компьютер....)
5. выбрать разные параметры поиска.
6. Нажать кнопку Искать
7. удостовериться что отображается хотя бы одно объявление

---
##### Task 2:
8. Отсортировать результаты по цене и выбрать закладку ‘продажа’.
9. Зайти в расширенный поиск.
10. Задать параметр поиска по цене от 0 до 300.
11. Выбрать не менее 3 любых объявлений.
12. Нажать кнопку Показать выбранные объявления. (proveritj chto cifra v skobkah praviljanaja)
13. Проверить, что объявления на странице совпадают с выбранными ранее
