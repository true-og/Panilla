# Panilla-OG
Panilla-OG (originally Panilla) is a combination of the word Packet and Vanilla (as in Vanilla Minecraft).

[Support the development of this plugin and purchase a precompiled .jar file on SpigotMC](https://www.spigotmc.org/resources/65694/) (or compile your own for free)


## Overview
Panilla-OG is software to prevent abusive NBT and packets on Minecraft servers.

With this software, you will be able to prevent:

- Unobtainable Enchantments (eg. Sharpness X)
- Unobtainable Potions (eg. Insta-kill)
- Unobtainable Fireworks
- Crash Books
- Crash Signs
- Crash Chests/Shulker Boxes
- Crash Potions (invalid CustomPotionColor\s)
- Oversized packets (which crash the client)
- Long item names/item lore
- Additional "AttributeModifiers" on items (eg. Speed)
- Unbreakable items
- and more abusive NBT

## Supported Platforms
Currently Panilla-OG supports:
- Bukkit
  - CraftBukkit* 1.8.8
  - CraftBukkit* 1.12.x-1.19.x
  
  **CraftBukkit includes any CraftBukkit derivatives (Spigot, Paper, Folia, etc)*

If you would like support for a server platform or Minecraft version that is not listed, please make an issue on GitHub.  

## Compiling
In order for you to compile Panilla-OG, you will need to use [BuildTools, by SpigotMC](https://www.spigotmc.org/wiki/buildtools), and build each version of CraftBukkit which is supported by Panilla-OG.
When you run BuildTools, it will add the dependencies required (CraftBukkit/Bukkit) to your local Maven repository.
From there, you can compile the project with `./gradlew build`. The installable plugin jar is written to `build/libs/Panilla-<version>-.jar`.

Gradle 8.14.3 with GraalVM Java 17 is required to build Panilla-OG.
