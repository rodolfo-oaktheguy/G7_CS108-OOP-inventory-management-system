# G7_CS108-OOP-inventory-management-system

Person A: Data classes (model)

-Product abstract class: private fields and validation in the setters and constructor (no negative price, stock never below 0)
-3 subclasses using super(...), each with its own extra field, plus the overridden methods
StockTransaction class
-8+ sample products
-When finished, A has the lightest load, so A also takes sort and the summary report inside Inventory

Person B: Inventory logic (the heaviest part)

-Product array: add (reject duplicate IDs, handle a full array), find, search, update, delete (shift elements so there are no gaps or nulls)
-Stock in/out: refuse a stock-out larger than the quantity, record a StockTransaction for every change, show the low-stock warning
-The recursive method, e.g. recursive total inventory value or recursive search

Person C: InventoryApp (UI)

-Input helpers: readInt, readDouble, readNonEmpty, readYesNo, readMenuChoice(min, max). Each must retry on bad input and never crash.
-do-while + switch menu covering all 12 features
-Aligned table output with printf
-C can build all of this against the stubs, so C doesn’t have to wait
