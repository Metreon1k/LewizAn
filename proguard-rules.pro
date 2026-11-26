### LewizAn — ProGuard rules
### Задача: защитить логику навигации, но не сломать загрузку мода Fabric'ом.

# Не оптимизируем и не удаляем код агрессивно — мод маленький, риск сломать
# тиковую логику важнее выигрыша в размере jar.
-dontoptimize
-dontshrink
-dontwarn **

# Точка входа указана по полному имени в fabric.mod.json — её класс,
# конструктор без аргументов и переопределённый метод нужно сохранить как есть,
# иначе Fabric Loader не найдёт мод при запуске.
-keep class dev.lewiz.an.LewizAnClient {
    public <init>();
    public void onInitializeClient();
}

# Это enum — ProGuard должен сохранить служебные values()/valueOf(),
# иначе Enum.valueOf и свитчи по enum сломаются после обфускации.
-keepclassmembers enum dev.lewiz.an.AnarchyMode {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Названия и номера строк оставляем читаемыми в логах/стектрейсах на вашей
# стороне, но сами имена классов/методов внутри мода можно переименовывать.
-keepattributes SourceFile, LineNumberTable
-renamesourcefileattribute SourceFile
