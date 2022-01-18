package com.example.nabosilka;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nabosilka.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    int zkouska;
    String prvniPriklad = null;
    String prvniZadani = null;
    ArrayList<Boolean> typyprikladu = new ArrayList<Boolean>();
    ArrayList<Integer> cinitele1 = new ArrayList<Integer>();
    ArrayList<String> vybervysledku = new ArrayList<String>();
    int cinitel2;
    ArrayList<Integer> cisla = new ArrayList<Integer>();
    int score = 0;
    int pocetprikladu = 0;
    boolean jenomNasobeni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void Vybirej(View view) {
// Tato meotda se zavolá po kliknutí na tlačítko a zjistí, které násobky chce uživatel procvičovat. A uloží je do seznamu čísla
// Na začátku je potřeba všechno vynulovat, aby nezbyly nějaké věci z minulého příkladu
        pocetprikladu = 0;
        typyprikladu.clear();
        cinitele1.clear();
        score = 0;
        cisla.clear();
        vybervysledku.clear();
        int pocetNasobku = 0;

        CheckBox checkBox = findViewById(R.id.Nuly);
        if (checkBox.isChecked() == true) {
            cisla.add(0); pocetNasobku++;
        }

        checkBox = findViewById(R.id.Jedne);
        if (checkBox.isChecked() == true) {
            cisla.add(1);pocetNasobku++;
        }

        checkBox = findViewById(R.id.Dvou);
        if (checkBox.isChecked() == true) {
            cisla.add(2);pocetNasobku++;
        }

        checkBox = findViewById(R.id.Tri);
        if (checkBox.isChecked() == true) {
            cisla.add(3);pocetNasobku++;
        }

        checkBox = findViewById(R.id.Ctyr);
        if (checkBox.isChecked() == true) {
            cisla.add(4);pocetNasobku++;
        }

        checkBox = findViewById(R.id.Peti);
        if (checkBox.isChecked() == true) {
            cisla.add(5);pocetNasobku++;
        }

        checkBox = findViewById(R.id.Sesti);
        if (checkBox.isChecked() == true) {
            cisla.add(6);pocetNasobku++;
        }

        checkBox = findViewById(R.id.Sedmi);
        if (checkBox.isChecked() == true) {
            cisla.add(7);pocetNasobku++;
        }

        checkBox = findViewById(R.id.Osmi);
        if (checkBox.isChecked() == true) {
            cisla.add(8);pocetNasobku++;
        }

        checkBox = findViewById(R.id.Deviti);
        if (checkBox.isChecked() == true) {
            cisla.add(9); pocetNasobku++;
        }

        checkBox = findViewById(R.id.Desiti);
        if (checkBox.isChecked() == true) {
            cisla.add(10); pocetNasobku++;
        }
// Pokud uživatel nevybere žádné násobky, zobrazí oznámení; že uživatel musí vybratel alespoň jeden násobek
        if (pocetNasobku == 0){
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setMessage("Aby šlo spustit hru, musíte vybrat alespoň jeden násobek")
                    .setPositiveButton("ok" , null )
                    .show();
            return;
        }

        //Tady se zjistí, jestli chce uživatel i příklady na dělění
        checkBox = findViewById(R.id.Ano);
        if (checkBox.isChecked() == true) {
            jenomNasobeni = false;
        } else {
            jenomNasobeni = true;
        }

// Následných několik řádků vytvoří seznam typu prikladu. Když je true znamená to násobení, pokud false znamená to dělení.
        if (jenomNasobeni == true) {
            int c = 0;
            while (c < 10) {
                typyprikladu.add(true);
                c++;
            }
            c = 0;
        }
        if (jenomNasobeni == false) {
            int i = 0;
            while (i < 5) {
                typyprikladu.add(true);
                typyprikladu.add(false);
                i++;
            }
            i = 0;
            //Zamíchá typy příkladů
            Collections.shuffle(typyprikladu);
        }

// Naplní seznam cinitele1 polozkami ze seznamu cisla.
        int nahoda = ThreadLocalRandom.current().nextInt(0, cisla.size());
        int j = 0;
        while (j < 10) {
            cinitele1.add(cisla.get(nahoda));
            nahoda = ThreadLocalRandom.current().nextInt(0, cisla.size());
            j++;
        }
        cisla.clear();

        cinitel2 = ThreadLocalRandom.current().nextInt(0, 10);
// Naplní seznam vybervysledku 4 čísly (Jeden správný výsledek a tři náhodný čísla). A do Stringu prvniPriklad uloží zadání prvního příkladu.
        if (typyprikladu.get(0) == true) {
            zkouska = cinitele1.get(0) * cinitel2;
            prvniPriklad = (cinitele1.get(0) + " * " + cinitel2);
            cinitele1.remove(0);
            cinitel2 = ThreadLocalRandom.current().nextInt(0, 11);
            vybervysledku.add(zkouska + "");
            nahoda = ThreadLocalRandom.current().nextInt(0, 101);
            vybervysledku.add(nahoda + "");
            nahoda = ThreadLocalRandom.current().nextInt(0, 101);
            vybervysledku.add(nahoda + "");
            nahoda = ThreadLocalRandom.current().nextInt(0, 101);
            vybervysledku.add(nahoda + "");
            Collections.shuffle(vybervysledku);

        }
        if (typyprikladu.get(0) == false) {
            zkouska = cinitele1.get(0) * cinitel2;
            prvniPriklad = (zkouska + " : " + cinitele1.get(0));
            zkouska = cinitel2;
            cinitele1.remove(0);
            cinitel2 = ThreadLocalRandom.current().nextInt(0, 11);
            vybervysledku.add(String.valueOf(zkouska));
            nahoda = ThreadLocalRandom.current().nextInt(0, 11);
            vybervysledku.add(String.valueOf(nahoda));
            nahoda = ThreadLocalRandom.current().nextInt(0, 11);
            vybervysledku.add(String.valueOf(nahoda));
            nahoda = ThreadLocalRandom.current().nextInt(0, 11);
            vybervysledku.add(String.valueOf(nahoda));
            Collections.shuffle(vybervysledku);

        }
        typyprikladu.remove(0);
// Přidá prvniPriklad do seznamu vyber vysledku
        vybervysledku.add(prvniPriklad);
// Vytvoří Bundle, který dostaane zprávu vybervysledku, a následně ji přenese do fragmentu vyberVysledkuFragment
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("Zpráva", vybervysledku);
//Nahodí nový fragment a předá mu první zadání a první výsledky
        Navigation.findNavController(view).navigate(R.id.vyberVysledkuFragment, bundle);

    }

    public void PsatVysledek(View view) {
// Tato meotda se zavolá po kliknutí na tlačítko a zjistí, které násobky chce uživatel procvičovat. A uloží je do seznamu čísla
// Na začátku je pořeba následující věci vynulovat
        cisla.clear();
        cinitele1.clear();
        pocetprikladu = 0;
        score = 0;
        Button button = findViewById(R.id.Dalsi);
        EditText editText = findViewById(R.id.Vysledek);
        ConstraintLayout a = findViewById(R.id.hahaha);
// Zjistí, které násobky, chce uživatel procvičovat
        CheckBox checkBox = findViewById(R.id.Nuly);
        if (checkBox.isChecked() == true) {
            cisla.add(0);
        }

        checkBox = findViewById(R.id.Jedne);
        if (checkBox.isChecked() == true) {
            cisla.add(1);
        }

        checkBox = findViewById(R.id.Dvou);
        if (checkBox.isChecked() == true) {
            cisla.add(2);
        }

        checkBox = findViewById(R.id.Tri);
        if (checkBox.isChecked() == true) {
            cisla.add(3);
        }

        checkBox = findViewById(R.id.Ctyr);
        if (checkBox.isChecked() == true) {
            cisla.add(4);
        }

        checkBox = findViewById(R.id.Peti);
        if (checkBox.isChecked() == true) {
            cisla.add(5);
        }

        checkBox = findViewById(R.id.Sesti);
        if (checkBox.isChecked() == true) {
            cisla.add(6);
        }

        checkBox = findViewById(R.id.Sedmi);
        if (checkBox.isChecked() == true) {
            cisla.add(7);
        }

        checkBox = findViewById(R.id.Osmi);
        if (checkBox.isChecked() == true) {
            cisla.add(8);
        }

        checkBox = findViewById(R.id.Deviti);
        if (checkBox.isChecked() == true) {
            cisla.add(9);
        }

        checkBox = findViewById(R.id.Desiti);
        if (checkBox.isChecked() == true) {
            cisla.add(10);
        }
// Pokud uživatel nevybere žádné násobky, zobrazí oznámení; že uživatel musí vybratel alespoň jeden násobek
        if (cisla.size() == 0){
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setMessage("Aby šlo spustit hru, musíte vybrat alespoň jeden násobek")
                    .setPositiveButton("ok" , null )
                    .show();
            return;
        }

//Pokud chce uživatel i příklady na dělení, uloží se false
        checkBox = findViewById(R.id.Ano);
        if (checkBox.isChecked() == true) {
            jenomNasobeni = false;
        } else {
            jenomNasobeni = true;
        }

// Následných několik řádků vytvoří seznam typu prikladu. Když je true znamená to násobení, pokud false znamená to dělení.
        if (jenomNasobeni == true) {
            int c = 0;
            while (c < 10) {
                typyprikladu.add(true);
                c++;
            }
            c = 0;
        }
        if (jenomNasobeni == false) {
            int i = 0;
            while (i < 5) {
                typyprikladu.add(true);
                typyprikladu.add(false);
                i++;
            }
            i = 0;
            //Zamíchá typy příkladů
            Collections.shuffle(typyprikladu);
        }

// Naplní seznam cinitele1 polozkami ze seznamu cisla.
        int nahoda = ThreadLocalRandom.current().nextInt(0, cisla.size());
        int j = 0;
        while (j < 10) {
            cinitele1.add(cisla.get(nahoda));
            nahoda = ThreadLocalRandom.current().nextInt(0, cisla.size());
            j++;
        }


        cisla.clear();

        TextView textView = findViewById(R.id.Zadani);
//Vytvoří první příklad a do zkoušky uloží správný výsledek příkladu
        cinitel2 = ThreadLocalRandom.current().nextInt(0, 11);
        if (typyprikladu.get(0) == true) {
            prvniZadani = (cinitele1.get(0) + " * " + cinitel2);
            zkouska = cinitele1.get(0) * cinitel2;
            cinitele1.remove(0);
            cinitel2 = ThreadLocalRandom.current().nextInt(0, 11);
        }
        if (typyprikladu.get(0) == false) {
            zkouska = cinitele1.get(0) * cinitel2;
            prvniZadani = (zkouska + " : " + cinitele1.get(0));
            zkouska = cinitel2;
            cinitele1.remove(0);
            cinitel2 = ThreadLocalRandom.current().nextInt(0, 11);
        }
        typyprikladu.remove(0);
// Vytvoří Bundle a zadá mu zprávu
        Bundle bundle = new Bundle();
        bundle.putString("Zpráva", prvniZadani);

//Nahodí nový fragment a předá mu první zadání (v bundlu)
        Navigation.findNavController(view).navigate(R.id.psaniVysledkuFragment, bundle);
    }


    public void ZkontrolujDalsi(View view) throws InterruptedException {
// Tato metoda se spustí po kliknutí na tlačítko další v modu hry, kde uživatel píše výsledek ručně
        TextView textView = findViewById(R.id.Zadani);
        Button button = findViewById(R.id.Dalsi);
        EditText editText = findViewById(R.id.Vysledek);
        ConstraintLayout a = findViewById(R.id.hahaha);
// Počet příkladů udává kolik příkladů už proběhlo
        pocetprikladu++;

// V této podmínce se dějí věci, které jsou potřeba na ukončení jednoho procvičování. Následně to uživatele vrátí zpět do počátečního fragmentu.
        if (button.getText().equals("Znovu")) {
            pocetprikladu = 0;
            score = 0;
            typyprikladu.clear();
            cinitele1.clear();
            Navigation.findNavController(view).navigate(R.id.nav_home);
        }
// V této podmínce se zkontoluje, jestli uživatel zadal správný výsledek
        if (button.getText().equals("Další")) {
            int napsano = Integer.parseInt(editText.getText().toString());
            if (napsano == zkouska) {
                editText.setText("Správně");
                a.setBackgroundResource(R.color.green);
                score++;
            } else {
                editText.setText("Správně je:" + zkouska);
                a.setBackgroundResource(R.color.red);
            }


            ColorDrawable viewColor = (ColorDrawable) a.getBackground();
            int colorId = viewColor.getColor();

            if (colorId == -15753896 || colorId == -65536) {
                Thread.sleep(1);
            }
            //a.setBackgroundResource(R.color.white);
            editText.setText("");
            if (typyprikladu.size() == 0) {
                textView.setText(score + " z " + pocetprikladu);
                editText.setText("Gratulujeme!");
                button.setText("Znovu");
            }
// Zobrazí nový příklad
            if (typyprikladu.size() > 0) {
                if (typyprikladu.get(0) == true) {
                    zkouska = cinitele1.get(0) * cinitel2;
                    textView.setText(cinitele1.get(0) + " * " + cinitel2);
                    cinitele1.remove(0);
                    cinitel2 = ThreadLocalRandom.current().nextInt(0, 11);
                }
                if (typyprikladu.get(0) == false) {
                    zkouska = cinitele1.get(0) * cinitel2;
                    textView.setText(zkouska + " : " + cinitele1.get(0));
                    zkouska = cinitel2;
                    cinitele1.remove(0);
                    cinitel2 = ThreadLocalRandom.current().nextInt(0, 11);
                }
                typyprikladu.remove(0);
            }


        }


    }

    public void Dalsi(View view) throws InterruptedException {
// Tato metoda se spustí v modu výběr výsledků po kliknutí na jakékoliv tlačítko
// Na začátku je potřeba následující věci vynulovat
        pocetprikladu++;
        vybervysledku.clear();
        int nahoda;
        Button tlacitko = findViewById(R.id.tlacitko1);
// Následujících pár řádků zjistí, které tlačítko bylo stisknutu a uloží ho do tlacitka
        switch (view.getId()) {
            case R.id.tlacitko1:
                tlacitko = findViewById(R.id.tlacitko1);
                break;
            case R.id.tlacitko2:
                tlacitko = findViewById(R.id.tlacitko2);
                break;
            case R.id.tlacitko3:
                tlacitko = findViewById(R.id.tlacitko3);
                break;
            case R.id.tlacitko4:
                tlacitko = findViewById(R.id.tlacitko4);
                break;
        }
        TextView textView = findViewById(R.id.Priklad);
        ConstraintLayout a = findViewById(R.id.hehehe);
        Button tlacitko1 = findViewById(R.id.tlacitko1);
        Button tlacitko2 = findViewById(R.id.tlacitko2);
        Button tlacitko3 = findViewById(R.id.tlacitko3);
        Button tlacitko4 = findViewById(R.id.tlacitko4);
// Vrátí se zpět na home fragment (na začátek hry)
        if (typyprikladu.size() == 0 && tlacitko1.getText().equals("Znovu")) {
            Navigation.findNavController(view).navigate(R.id.nav_home);
        }
// Tento kus kódu se provede po posledním příkladu
        if (typyprikladu.size() == 0 && !(tlacitko1.getText().equals("Znovu"))) {
            int napsano = Integer.parseInt(tlacitko.getText().toString());
            if (napsano == zkouska) {
                score++;
                a.setBackgroundResource(R.color.green);
            } else {
                a.setBackgroundResource(R.color.red);
            }
            Thread.currentThread().sleep(2000);
            a.setBackgroundResource(R.color.white);
            textView.setText(score + " z " + pocetprikladu);
            tlacitko1.setTextSize(40);
            tlacitko2.setTextSize(40);
            tlacitko3.setTextSize(40);
            tlacitko4.setTextSize(40);
            tlacitko1.setText("Znovu");
            tlacitko2.setText("Znovu");
            tlacitko3.setText("Znovu");
            tlacitko4.setText("Znovu");
        }
// Zjistí jestli uživatel zadal správný výsledek a zobrazí další příklad
        if (typyprikladu.size() > 0) {
            int napsano = Integer.parseInt(tlacitko.getText().toString());
            if (napsano == zkouska) {
                score++;
                a.setBackgroundResource(R.color.green);
            } else {
                a.setBackgroundResource(R.color.red);
            }
            Thread.currentThread().sleep(2000);
            //a.setBackgroundResource(R.color.white);
            if (typyprikladu.get(0) == true) {
                zkouska = cinitele1.get(0) * cinitel2;
                textView.setText(cinitele1.get(0) + " * " + cinitel2);
                cinitele1.remove(0);
                cinitel2 = ThreadLocalRandom.current().nextInt(0, 11);
                vybervysledku.add(zkouska + "");
                nahoda = ThreadLocalRandom.current().nextInt(0, 101);
                vybervysledku.add(nahoda + "");
                nahoda = ThreadLocalRandom.current().nextInt(0, 101);
                vybervysledku.add(nahoda + "");
                nahoda = ThreadLocalRandom.current().nextInt(0, 101);
                vybervysledku.add(nahoda + "");
                Collections.shuffle(vybervysledku);
                tlacitko1.setText(String.valueOf(vybervysledku.get(0)));
                vybervysledku.remove(0);
                tlacitko2.setText(String.valueOf(vybervysledku.get(0)));
                vybervysledku.remove(0);
                tlacitko3.setText(String.valueOf(vybervysledku.get(0)));
                vybervysledku.remove(0);
                tlacitko4.setText(String.valueOf(vybervysledku.get(0)));
                vybervysledku.remove(0);
            }
            if (typyprikladu.get(0) == false) {
                zkouska = cinitele1.get(0) * cinitel2;
                textView.setText(zkouska + " : " + cinitele1.get(0));
                zkouska = cinitel2;
                cinitele1.remove(0);
                cinitel2 = ThreadLocalRandom.current().nextInt(0, 11);
                vybervysledku.add(zkouska + "");
                nahoda = ThreadLocalRandom.current().nextInt(0, 11);
                vybervysledku.add(nahoda + "");
                nahoda = ThreadLocalRandom.current().nextInt(0, 11);
                vybervysledku.add(nahoda + "");
                nahoda = ThreadLocalRandom.current().nextInt(0, 11);
                vybervysledku.add(nahoda + "");
                Collections.shuffle(vybervysledku);
                tlacitko1.setText(String.valueOf(vybervysledku.get(0)));
                vybervysledku.remove(0);
                tlacitko2.setText(String.valueOf(vybervysledku.get(0)));
                vybervysledku.remove(0);
                tlacitko3.setText(String.valueOf(vybervysledku.get(0)));
                vybervysledku.remove(0);
                tlacitko4.setText(String.valueOf(vybervysledku.get(0)));
                vybervysledku.remove(0);
            }
            typyprikladu.remove(0);

        }

    }

}