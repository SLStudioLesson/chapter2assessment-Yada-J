package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RecipeFileHandler {
    private String filePath;

    public RecipeFileHandler() {
        filePath = "app/src/main/resources/recipes.txt";
    }

    public RecipeFileHandler(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 設問1: 一覧表示機能
     * recipes.txtからレシピデータを読み込み、それをリスト形式で返します。 <br> 
     * IOExceptionが発生したときは<i>Error reading file: 例外のメッセージ</i>とコンソールに表示します。
     *
     * @return レシピデータ
     */
    //readRecipesメソッド
    public ArrayList<String> readRecipes() {

        ArrayList<String> recipes =  new ArrayList<>();
        //recipes.txtからレシピデータを読み込み、それをリスト形式で返します。
        try  (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            //ファイルの中の行がなくなるまで継続
            while ((line = reader.readLine()) != null) {
                //リストに追加
                recipes.add(line);
            }
        } catch (IOException e) {
                    //IOExceptionが発生したときはError reading file: 例外のメッセージとコンソールに表示します。
            System.out.println("Error reading file:" + e.getMessage());
        }
        //リストを返す
        return recipes;
    }

    /**
     * 設問2: 新規登録機能
     * 新しいレシピをrecipes.txtに追加します。<br>
     * レシピ名と材料はカンマ区切りで1行としてファイルに書き込まれます。
     *
     * @param recipeName レシピ名
     * @param ingredients 材料名
     */
     // 
    //addReicpeメソッド
    public void addRecipe(String recipeName, String ingredients) {
        //レシピ名と材料はカンマ区切りで1行とする
        String writeString = String.join(",", recipeName, ingredients);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            //新しいレシピを書き込む
            writer.write(writeString);
            //書き込み後に改行する
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error reading file: 例外のメッセージ" + e.getMessage());
        }
    }
}
