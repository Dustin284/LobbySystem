# LobbySystem
Das LobbySystem ist ein Minecraft Plugin für den Lobby Server, welches entwickelt wurde um eine benutzerfreundliche und skalierbare Lobby-Funktionalität in deine Anwendung zu integrieren.

## Funktione
- Chat-Funktionalität innerhalb der Lobbies
- Unterstützung der folgenden Befehle:
- /setspawn <spawn/freebuild/skypvp> - Setzt den Spawn-Punkt für den angegebenen Spielmodus.
- /spawn - Teleportiert den Spieler zum Lobby-Spawn-Punkt.
- /build - Erlaubt dem Spieler den Bau-Modus zu aktivieren.
- /fly - Erlaubt dem Spieler das Fliegen.
- /coins - Zeigt die Menge der Spieler-Coins an.
- /stats - Zeigt die Statistiken des Spielers an.
- /perks - Zeigt die verfügbaren Perks des Spielers an.
- Integration von DiscordWebhook zur Benachrichtigungsfunktion.
- Verwendung einer MySQL-Datenbank zur Speicherung von Daten.
- Integration eines Scoreboards und einer Tablist.

## Installation
1. Lade die neueste Version des LobbySystem-Releases von [hier](https://github.com/Dustin284/LobbySystem/releases) herunter.

2. Konfiguriere deine MySQL-Datenbank-Zugangsdaten:
3. Navigiere zum Ordner LobbySystem im Plugins-Verzeichnis.
4. Öffne die Datei MySQL.yml mit einem Texteditor.
5. Bearbeite die entsprechenden Felder für den MySQL-Datenbankzugriff und speichere die Datei.
6. Starte den Lobby-Server

## Berechtigungen
| Berechtigung                       | Bezeichnung               |
|------------------------------------|---------------------------|
| lobbysystem.*                      | Alle LobbySystem-Berechtigungen |
| lobbysystem.build                  | Build-Befehl              |
| lobbysystem.setspawn               | Setzen des Spawn-Punkts    |
| lobbysystem.rank                   | Rank-Befehl              |
| lobbysystem.perks.speed            | Geschwindigkeits-Perk      |
| lobbysystem.perks.jumpboost        | Sprungkraft-Perk           |
| lobbysystem.perks.fly              | Flug-Perk                  |
| lobbysystem.perks.ender_perl       | Enderperlen-Perk           |
| lobbysystem.perks.grabbling_hook   | Greifhaken-Perk            |
| lobbysystem.coins.add              | Spieler-Coins hinzufügen   |
| lobbysystem.coins.remove           | Spieler-Coins entfernen    |
| lobbysystem.coins.set              | Spieler-Coins festlegen    |
| lobbysystem.chat.color             | Farbige Chatnachrichten             |
|------------------------------------|---------------------------|

<details>
  <summary>Scoreboard-Spoiler</summary>
[Scoreboard](https://prnt.sc/nHJsFCAqZyO_)
</details>
