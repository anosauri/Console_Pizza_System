# System zamawiania pizzy

Program umożliwia zarządzanie zamówieniami w pizzerii poprzez konsolowe menu. Użytkownik może tworzyć zamówienia, wybierać gotowe pizze lub komponować własne, stosować promocje, zarządzać zamówieniami i śledzić ich status.
## Funkcjonalność
- **Tworzenie nowego zamówienia:**
  - Pobieranie danych klienta (np. imię, nazwisko, kontakt)
  - Inicjowanie pustego zamówienia powiązanego z klientem

- **Dodawanie pizzy do zamówienia:**
  - Wybór gotowej pizzy z menu (każda pizza to osobna klasa dziedzicząca po abstrakcyjnej klasie `Pizza`, np. `Margherita`, `Pepperoni`, `Farmerska`, `Vegetariana`, `Serowy Kurczak`, `Serowa`)
  - Tworzenie własnej kompozycji pizzy (wybór rozmiaru oraz składników)

- **Usuwanie pizzy z zamówienia**

- **Wybór promocji:**
  - Brak promocji
  - Zniżka procentowa
  - Kup 2, trzecia gratis
  - Darmowa dostawa

- **Wyliczanie ceny zamówienia na podstawie składników, rozmiaru pizzy oraz zastosowanej promocji**

- **Zarządzanie statusami zamówienia:**
  - `ORDERING` – w trakcie składania
  - `ORDER_PLACED` – złożone
  - `IN_PREPARATION` – w przygotowaniu
  - `READY` – gotowe
  - `CANCELLED` – anulowane

- **Automatyczna zmiana statusów zamówienia w czasie** (symulacja kolejnych etapów przygotowania)

- **Możliwość anulowania zamówienia przed jego przygotowaniem**

- **Podgląd statusu zamówienia po numerze**

- **Wyświetlanie kolejki wszystkich zamówień**

## Struktura projektu

- **`PizzeriaSystem.java`** – główny interfejs aplikacji, zawierający pętlę menu oraz obsługę interakcji z użytkownikiem
- **`OrderManager.java`** – logika zarządzania zamówieniami: tworzenie, przetwarzanie, aktualizacja statusów
- **`Order.java`** – reprezentacja pojedynczego zamówienia, zawiera m.in. dane klienta, wybraną pizzę i promocję
- **`Pizza.java`** – klasa abstrakcyjna definiująca wspólne cechy wszystkich pizz
  - **`CustomPizza.java`** – reprezentuje pizzę tworzoną samodzielnie przez użytkownika
  - **`Margherita.java`**, **`Pepperoni.java`**, **`Farmerska.java`**, **`Serowa.java`**, **`SerowyKurczak.java`**, **`Vegetariana.java`** – konkretne warianty pizz, tworzone na podstawie `Pizza.java`
- **`PizzaFactory.java`** – fabryka odpowiedzialna za tworzenie instancji pizz, zarówno gotowych, jak i niestandardowych oraz magazyn składników
- **`Ingredient.java`** – reprezentuje pojedynczy składnik pizzy, wykorzystywany głównie przy tworzeniu `CustomPizza`
- **`TypeOfIngredients.java`** – enum określający typ składnika (np. ser, mięso, warzywo)
- **`Client.java`** – przechowuje dane klienta składającego zamówienie
- **`PromotionType.java`** – enum zawierający dostępne typy promocji w systemie
- **`Size.java`** – enum definiujący rozmiary pizzy
- **`OrderStatus.java`** – enum opisujący możliwe statusy zamówienia
- **`Main.java`** – punkt startowy aplikacji, może zawierać uruchomienie `PizzeriaSystem`
- **`README.md`** – dokumentacja projektu z opisem działania i struktury kodu

## Uruchomienie

1. Pobierz i rozpakuj projekt z pliku `system_zamawiania_pizzy_Dec_Pienkowska.zip`.
2. Otwórz folder, w którym znajdują się pliki `.java`.
3. Skompiluj wszystkie pliki:
  - Jeśli używasz terminala, skorzystaj z odpowiednich komend kompilacji.
  - Jeśli wolisz IDE, kliknij prawym przyciskiem myszy na folderze i wybierz opcję kompilacji.
4. Uruchom klasę `Main.java`.
5. Korzystaj z numerowanych opcji wyświetlanych w menu.

## Wymagania

- Java 8 lub nowsza
- Konsola do obsługi wejścia tekstowego

## Informacje dodatkowe

System wykorzystuje prosty symulator czasu, aby aktualizować statusy zamówień automatycznie po złożeniu. Promocje działają zależnie od warunków (np. 3 pizze wymagane do "2+1 gratis"). Przy tworzeniu własnej pizzy wymagane są przynajmniej dwa składniki - jakiś sos oraz jeden dowolny, a maksymalna ilość to 7.
