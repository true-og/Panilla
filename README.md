# Panilla-OG

A fork of [Panilla](https://github.com/ds58/Panilla) by [TrueOG Network](https://true-og.net) maintained for Purpur 1.19.4. Panilla is a combination of the word Packet and Vanilla (as in Vanilla Minecraft).

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
- Purpur 1.19.4

## Compiling
After installing Spigot BuildTools to your mavenLocal (or running the TrueOG Bootstrap), you can compile the project with `./gradlew build clean eclipse`. The installable plugin jar is written to `build/libs/Panilla-<version>-.jar`.

Gradle 8.14.3 with GraalVM Java 17 is required to build Panilla-OG.

## License

MIT
