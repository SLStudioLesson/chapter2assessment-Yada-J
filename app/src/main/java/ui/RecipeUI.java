package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import data.RecipeFileHandler;

public class RecipeUI {
    private BufferedReader reader;
    private RecipeFileHandler fileHandler;

    public RecipeUI() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        fileHandler = new RecipeFileHandler();
    }

    public RecipeUI(BufferedReader reader, RecipeFileHandler fileHandler) {
        this.reader = reader;
        this.fileHandler = fileHandler;
    }

    public void displayMenu() {
        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        // 設問1: 一覧表示機能
                        //displayRecipesメソッドの呼び出し
                        displayRecipes();
                        break;
                    case "2":
                        // 設問2: 新規登録機能
                        //addNewRecipeメソッドの呼び出し
                        addNewRecipe();
                        break;
                    case "3":
                        // 設問3: 検索機能
                        break;
                    case "4":
                        System.out.println("Exit the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    /**
     * 設問1: 一覧表示機能
     * RecipeFileHandlerから読み込んだレシピデータを整形してコンソールに表示します。
     */
    //displayRecipesメソッド
    private void displayRecipes() {
        //RecipeFileHandlerからリストの読み込み
        ArrayList<String> recipes =  fileHandler.readRecipes();
        if (recipes == null || recipes.isEmpty()) {
            //読み込んだレシピデータが空の場合は、 No recipes available. というメッセージを出力します。
            System.out.println("No recipes available.");
        } else {
            System.out.println("Recipes:");
            System.out.println("-----------------------------------");
            //レシピの整形
            for (String recipe : recipes) {
                //最初の,でレシピと材料の2分割にして配列に入れる
                String[] keyValue = recipe.split("," ,2) ;
                //配列数が２なら１つ目をRecipe Name、２つ目をMain Ingredientsとして出力
                if (keyValue.length == 2) {
                    String recipeName = keyValue[0];
                    String ingredients = keyValue[1];
                    System.out.println("Recipe Name: " + recipeName);
                    System.out.println("Main Ingredients: " + ingredients);
                    System.out.println("-----------------------------------");
                }
            }
        }
    }

    /**
     * 設問2: 新規登録機能
     * ユーザーからレシピ名と主な材料を入力させ、RecipeFileHandlerを使用してrecipes.txtに新しいレシピを追加します。
     *
     * @throws java.io.IOException 入出力が受け付けられない
     */
    //addNewRecipeメソッド
    private void addNewRecipe() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //レシピ名の入力受付
        System.out.print("Enter recipe name:");
        String recipeName = reader.readLine();
        //具材の入力受付
        System.out.print("Enter main ingredients (comma separated):");
        String ingredients = reader.readLine();

        //RecipeFileHandlerを使用してrecipes.txtに新しいレシピを追加
        fileHandler.addRecipe(recipeName, ingredients);

        //追記の成功メッセージ
        System.out.println("Recipe added successfully.");
    }

    /**
     * 設問3: 検索機能
     * ユーザーから検索クエリを入力させ、そのクエリに基づいてレシピを検索し、一致するレシピをコンソールに表示します。
     *
     * @throws java.io.IOException 入出力が受け付けられない
     */
    private void searchRecipe() throws IOException {

    }

}

