package com.viralandroid.receipe;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by T on 14-02-2017.
 */

public class RecipeFragment extends FragmentActivity {
    ImageView back_btn;
    ImageView recipe_cart;
    FrameLayout recipe_frame;
    LinearLayout recipes,ingrediants,methods;
    ImageView recipe_image,ingrediiants_image,methods_image;
    TextView recipe_title,ingrediants_title,methods_title;
    private String holder;
    Recipes recipes_obj;
    ImageView recipe_like,recipe_share;
    TextView recipe_gluten,recipe_ct,recipe_portions,recipe_calories;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_fragment);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        recipe_cart = (ImageView) findViewById(R.id.recipe_cart);

        recipe_frame = (FrameLayout) findViewById(R.id.recipe_frame);
        recipes = (LinearLayout) findViewById(R.id.recipes);
        ingrediants = (LinearLayout) findViewById(R.id.ingrediants);
        methods  = (LinearLayout) findViewById(R.id.methods);
        recipe_image = (ImageView) findViewById(R.id.recipe_image);
        ingrediiants_image = (ImageView) findViewById(R.id.ingrediants_image);
        methods_image = (ImageView) findViewById(R.id.methods_image);
        recipe_title = (TextView) findViewById(R.id.recipe_title);
        ingrediants_title = (TextView) findViewById(R.id.ingrediants_title);
        methods_title = (TextView) findViewById(R.id.methods_title);

        recipe_gluten = (TextView) findViewById(R.id.recipe_gluten);
        recipe_ct = (TextView) findViewById(R.id.recipe_ct);
        recipe_portions = (TextView) findViewById(R.id.recipe_portions);
        recipe_calories = (TextView)  findViewById(R.id.recipe_calories);

        try {
            recipes_obj = (Recipes) getIntent().getSerializableExtra("recipe");
        } catch (Exception e) {
            e.printStackTrace();
        }

        recipe_gluten.setText(recipes_obj.gluten);
        recipe_ct.setText(recipes_obj.cooking_time);
        recipe_portions.setText(recipes_obj.portions);
        recipe_calories.setText(recipes_obj.calories);

        reset_icons(2);
        reset_text_color(2);
        IngrediantsListFragment ingrediantsListFragment = new IngrediantsListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.recipe_frame,ingrediantsListFragment).commit();

        recipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset_icons(1);
                reset_text_color(1);
                RecipeListFragment recipeListFragment = new RecipeListFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("recipe",recipes_obj);
                recipeListFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.recipe_frame,recipeListFragment).commit();
            }
        });

        ingrediants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset_icons(2);
                reset_text_color(2);
                IngrediantsListFragment ingrediantsListFragment = new IngrediantsListFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.recipe_frame,ingrediantsListFragment).commit();
            }
        });

        methods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset_icons(3);
                reset_text_color(3);
                MethodsListFragment methodsListFragment = new MethodsListFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.recipe_frame,methodsListFragment).commit();
            }
        });

        recipe_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(RecipeFragment.this,CartFragment.class);
                startActivity(intent);
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecipeFragment.this,ProductsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void reset_icons(int pos){

        recipe_image.setImageResource(R.drawable.tab_bar_summary2);
        ingrediiants_image.setImageResource(R.drawable.tab_bar_ingredients2x1);
        methods_image.setImageResource(R.drawable.tab_bar_recipe);

        switch (pos){
            case  1:
                recipe_image.setImageResource(R.drawable.tab_bar_ingredients2x3);
                break;
            case  2:
                ingrediiants_image.setImageResource(R.drawable.tab_bar_ingredients_active);
                break;
            case  3:
                methods_image.setImageResource(R.drawable.tab_bar_ingredients2x);
                break;
        }

    }

    private void reset_text_color(int pos){
        recipe_title.setTextColor(Color.parseColor("#8a000000"));
        ingrediants_title.setTextColor(Color.parseColor("#8a000000"));
        methods_title.setTextColor(Color.parseColor("#8a000000"));

        switch (pos) {
            case 1:
                recipe_title.setTextColor(Color.parseColor("#ffa500"));
                break;
            case 2:
                ingrediants_title.setTextColor(Color.parseColor("#ffa500"));
                break;
            case 3:
                methods_title.setTextColor(Color.parseColor("#ffa500"));
                break;
        }
    }
}
