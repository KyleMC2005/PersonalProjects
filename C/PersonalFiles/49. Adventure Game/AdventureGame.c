#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
#include <ctype.h>

// What are the key things in an adventure game?
// Player and Enemy Stats:
// Name
// Level
// Health
// Gold

// Items:
// Health Potion
// Accuracy Potion
// Speed Potiona
// Strength 
// Ironskin (Defence)
// Thorns (Reflective Damage)
// Invisibility (Greater Dodge Chance)
// Tiers: Lesser, Regular, Greater, Super

// Combat Stats:
// HitChance
// Speed
// Damage 
// Defence
// Dodge

// Enemy types 
// Mugger (Easiest)
// Warrior

// Current goal, functional combat (Basic's have been complete, now implement 'Item', 'Defence', and 'Run')
// Save and Load function, using read and write

// Attack
// Defence
// Items
// Run

typedef struct 
{
    char Name[25];                              // Array to store the players Name
    int Level;
    int Health;
    int Gold;
    int Attack;
    int XP;                                     // Current XP
    int XPProc;                                 // XP Limit for a level up
}Player;

typedef struct 
{
    char Name[25];                              // Array to store the Enemy Name, 10 names (randomised)
    char type[25];                              // Array to store the Enemy Types
    int Level;
    int Health;
    int Gold;
    int Attack;
}Enemy;

typedef struct 
{
    int Health;
    int Attack;
}Train;


// Function Prototypes
void EnemyStats();
void EnemyEncounter(Player *Adventurer);
void PracticeCombat(Player *Adventurer);
void InitiateCombat(Player *Adventurer);        // Pointer so I can continue to modify main Adventurer in Combat function



int main()
{
    char play;
    srand(time(0));

    Player Adventurer;
    Train Training;

    printf("Hello adventurer! What is your name?: ");
    fgets(Adventurer.Name, sizeof(Adventurer.Name), stdin);     // Get's the character name and stores it in the Struct
    Adventurer.Name[strcspn(Adventurer.Name, "\n")] = '\0';     // fgets() leaves the newline character, so let's remove it
    printf("Glad to meet you, %s!\n", Adventurer.Name);         // Send back a message

    Adventurer.Health = 100;                                     // Set stats
    Adventurer.Level = 1;
    Adventurer.Attack = 5;
    Adventurer.Gold = 0;
    Adventurer.XP = 0;
    Adventurer.XPProc = 100;

    printf("It is about time to fight your first enemy! Here, have a go against this Training Dummy to practice!\n");
    printf("\n");

    // Function Call
    printf("Are you ready?: (Y/N)\n");
    scanf("%c", &play);
    play = toupper(play);
    if(play == 'Y'){
        PracticeCombat(&Adventurer);
    }
    else {
        printf("\nThanks for playing!");
        exit(0);
    }
    InitiateCombat(&Adventurer);

    return 0;
}

void PracticeCombat(Player *Adventurer)
{
    printf("You have encounted a training dummy!\n");

    Train Training;
    Training.Health = 25;
    Training.Attack = 0;

    do {
        int i = rand() % 2 + 1;
        if (i == 1) {
            Training.Health -= Adventurer->Attack;
            printf("\n%s has dealt %d damage to the training dummy!\n", Adventurer->Name, Adventurer->Attack);
        } else if (i == 2) {
            Adventurer->Health -= Training.Attack;
            printf("\nThe training dummy has dealt %d damage to %s!\n", Training.Attack, Adventurer->Name);
        }
        printf("Adventurer Health: %d\n", Adventurer->Health);
        printf("Opponent Health: %d\n", Training.Health);
    } while (Adventurer->Health > 0 && Training.Health > 0);

    if (Adventurer->Health <= 0) {
        printf("\n%s has died! How the hell did you die to a fucking training dummy?\n", Adventurer->Name);
    } else if (Training.Health <= 0) {
        printf("\nThe training dummy has been defeated!\n");
        printf("You have not earned any Gold due to this being a training session!\n");
        printf("Gold: %d\n", Adventurer->Gold);
}
}


void InitiateCombat(Player *Adventurer)
{
    // Setting Enemy Type Stats
    Enemy Opponent;                                             // Current Enemy
    Enemy Mug;                                                  // Mugger Enemy Type
    Enemy War;                                                  // Warrior Enemy Type

    Mug.Level = rand() % 5 + 1;                                 // Sets level between 1-5
    Mug.Health = (5 + (2 * Mug.Level));
    Mug.Attack = (1 + (2 * Mug.Level));
    strcpy(Mug.type, "Mugger");
    Mug.Gold = rand() % 5 + 1;

    War.Level = rand() % 6 + 5;                                // Sets level between 5-10
    War.Health = (10 + (2 * War.Level));
    War.Attack = (5 + (2 * War.Level));
    strcpy(War.type, "Warrior");
    War.Gold = rand() % 15 + 5;

    int j = rand() % 2 + 1;

    if (j == 1) {
        Opponent = Mug;                                         // RNG decides if the opponent is a mugger or a warrior
    } else {
        Opponent = War;
    }

    strcpy(Opponent.Name, "Fak");
    printf("\n%s has encountered a Level %d %s the %s!\n", Adventurer->Name, Opponent.Level, Opponent.Name, Opponent.type);

    printf("Hero Health: %d\n", Adventurer->Health);
    printf("Opponent Health: %d\n", Opponent.Health);

    do {
        int i = rand() % 2 + 1;
        if (i == 1) {
            Opponent.Health -= Adventurer->Attack;
            printf("\n%s has dealt %d damage to %s!\n", Adventurer->Name, Adventurer->Attack, Opponent.Name);
        } else if (i == 2) {
            Adventurer->Health -= Opponent.Attack;
            printf("\n%s has dealt %d damage to %s!\n", Opponent.Name, Opponent.Attack, Adventurer->Name);
        }
        printf("Adventurer Health: %d\n", Adventurer->Health);
        printf("Opponent Health: %d\n", Opponent.Health);
    } while (Adventurer->Health > 0 && Opponent.Health > 0);

    if (Adventurer->Health <= 0) {
        printf("\n%s has died! Skill issue.\n", Adventurer->Name);
    } else if (Opponent.Health <= 0) {
        printf("\n%s the %s has been defeated!\n", Opponent.Name, Opponent.type);
        Adventurer->Gold += Opponent.Gold;
        printf("You have earned %d Gold!\n", Opponent.Gold);
        printf("Gold: %d\n", Adventurer->Gold);

        // Reward XP based on opponent level
        if (Opponent.Level >= 1 && Opponent.Level <= 5) {
            Adventurer->XP += rand() % 30 + 10; // Rewards 10-30 XP for enemies from Level 1 to 5
        } else if (Opponent.Level >= 5 && Opponent.Level <= 10) {
            Adventurer->XP += rand() % 50 + 30; // Rewards 30-50 XP for enemies from Level 5 to 10
        }

        printf("XP: %d / %d\n", Adventurer->XP, Adventurer->XPProc);

        // Level-up logic
        while (Adventurer->XP >= Adventurer->XPProc) {
            Adventurer->XP -= Adventurer->XPProc;
            Adventurer->Level++;
            Adventurer->XPProc += 100 * Adventurer->Level; // Increase XP requirement for the next level
            Adventurer->Health += 20; // Increase health for leveling up
            Adventurer->Attack += 2;  // Increase attack power for leveling up
            printf("%s has leveled up to Level %d!\n", Adventurer->Name, Adventurer->Level);
            printf("XP: %d / %d\n", Adventurer->XP, Adventurer->XPProc);
            printf("Health: %d\n", Adventurer->Health);
            printf("Attack: %d\n", Adventurer->Attack);
        }
    }
}
