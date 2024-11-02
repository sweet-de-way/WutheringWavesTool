# Quick Start

!> All documents are translated by AI.

> By following this quick start guide, you will be able to quickly get started with the Assistant.

## System Requirements

| **Requirement**        | Specification                               |
| ---------------------- | ------------------------------------------- |
| Minimum System Version | Windows 10 Build 1809 (22H2)<br/>Windows 11 |
| Recommended Version    | Windows 11                                  |

*Versions below Windows 10 Build 1809 cannot start by default, but can be launched by modifying the assistant's configuration. Please refer to the FAQ for details.*



## Download Assistant

The Assistant is designed to be lightweight and simple, available for immediate use after extraction, hence it is released as a zip file without an installer.

Visit the [GitHub Release Page](https://github.com/leck995/WutheringWavesTool/releases) to download the latest WutheringWavesTool.zip. After downloading and extracting the zip file, launch **WutheringWavesTool.exe**.

*You can also start the assistant using **App.exe**, which is a built-in alternative method, but it is not recommended unless there are issues with launching WutheringWavesTool.exe.*

> The current assistant does not support automatic updates. To update, download the latest zip file and overwrite the old version. Overwriting does not affect existing data and will not result in data loss.

## Complete Basic Game Settings

Upon first launch, please go to the **Settings** interface to complete the settings related to the game root directory.

For selecting the game server, players on the Chinese server can simply choose **Default**. For other servers, please select according to the setup text. **Please ensure to select the correct server, as an incorrect selection may cause the assistant to malfunction.**

The current game root directory is the directory where the Mingchao launcher is located, not the directory where the game itself is installed. For example, if the launcher is installed in C:\Game\Wuthering Waves, simply select the Wuthering Waves folder.

There is no need to change the game launch program; keeping it default is sufficient.





## FAQ

#### 1. Prompt says unable to retrieve gacha data connection

***Make sure the game installation root directory is correct, ensure the game installation root directory is correct, ensure the game installation root directory is correct;***

You must open the gacha history interface in the game first.

If you have previously modified the game configuration, such as unlocking frame rates, check WutheringWaves\Wuthering Waves Game\Client\Saved\Config\WindowsNoEditor for Engine.ini, find and remove global=none.

If the above does not resolve the issue, delete Wuthering Waves\Wuthering Waves Game\Client\Saved\Logs and try again.


#### 2. Account has been added and set as the main account, but still prompts that the account does not exist

Try restarting the assistant. If the issue persists, please submit an issue.

#### 3.  I am on the international server and cannot use the Library District; what should I do about the popup reminders? What features can the international server use?？

International server players can enable the disable Library District option in Settings - Basic Settings to stop receiving related popups. Additionally, after disabling the Library District, all features will be available for use by international server players.

#### 4. Can I perform a clean installation of a new version? How do I migrate old assistant data without overwriting?

Unless otherwise specified, overwriting installations are supported. If both old and new versions exist, to migrate data from the old version to the new version, just copy the **data folder**, the **setting.json file**, and the **sqlite.db file** from the assistant's installation directory to the new version's directory.

#### 5.Where is the assistant's data stored? How can I transfer data to ensure it is not lost when switching computers or reinstalling the assistant?

All data for the assistant is stored in the assistant installation directory. To migrate data, simply copy the **data folder**, the **setting.json file**, and the **sqlite.db file** to the new version's assistant installation directory.

#### 6. How to launch the assistant from the desktop?

Right-click the Mingchao Assistant launch program, create a shortcut, and then drag the shortcut to the desktop.

#### 7. Unable to open the assistant; it crashes immediately with no response?

The assistant only supports Windows 10 and above. Please check if your system version meets the requirements. If your Windows 10 version is below 1809, you need to open the assistant installation directory's **app/WutheringWavesTool.cfg** file with Notepad and delete the following text:
```
[JavaOptions]
java-options=-Djpackage.app-version=1.0
java-options=-XX:+UseZGC
```
*If you are using a third-party modified or trimmed system, please do not report or submit issues, as these are not supported by the assistant.*

#### 8.Frequently prompted about network exceptions and unable to check for new versions; what should I do?

Due to certain user network environments, it may not be possible to successfully retrieve update data. You can go to Settings to disable update checking. Please note that once enabled, the assistant will not prompt for future updates, and you will need to manually download from GitHub.

#### 9. The assistant has two title bars; how can I resolve this?

Open Settings and enable the new status bar.。

#### 10.Prompt says unable to access remote resource repository; check the network and set the repository source correctly; what should I do?
You can try switching the resource repository in Settings.

#### 11.   Reminder about multi-instance restrictions

This is not an issue with the assistant. Please restart your computer or close all background processes related to Mingchao in the Task Manager.

#### 12. Using the assistant to launch the game causes lag

The assistant will launch the game using the official default DX method (depending on your computer configuration and launch program). You can specify the DX version in the advanced launch settings.



