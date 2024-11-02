# Gacha Analysis

!> All documents are translated by AI.

> The current assistant's gacha analysis UI lacks design. If you have experience in UI design and are willing to help, please contact the developer.

Mingchao only retains gacha history for six months and lacks intuitive and effective analysis features. The assistant provides a simple function to save gacha data, allowing you to view all historical records even beyond six months.

<img src="./assets/image-20241102134437388.png" alt="image-20241102134437388" style="zoom:67%;" />

Through gacha analysis, you can:

* Retrieve and save gacha data
* Analyze gacha records

All gacha records are saved in JSON format text to the **assistant installation directory/data directory**, organized by game ID.

> To ensure data security, starting from version 1.1.9 of the assistant, each time new gacha history is retrieved, the previous day's records will be automatically backed up. If data errors occur, you can manually open the **data directory** and the corresponding ID directory to restore the backed-up JSON files.

