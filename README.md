# Wolf Custom VPN 🐺

تطبيق أندرويد متطور لمشاركة وتأمين الاتصال (VPN Tethering & Hotspot) مبني بلغة Kotlin و Jetpack Compose مع ميزات حماية متقدمة.

## الميزات والتعديلات المطبقة:
1. **Package Name**: تم التحديث إلى `com.wolfcustom.vpn` بدلاً من `dev.shizzi`.
2. **اسم التطبيق**: `Wolf Custom VPN`.
3. **التحقق الإجباري من التطبيق**:
   - يتحقق التطبيق تلقائياً عند الإقلاع من وجود الحزمة المطلوبة `core.vpn3jx.xtreme`.
   - في حال عدم التثبيت يتم حظر الدخول وعرض رسالة "يجب تثبيت التطبيق المطلوب أولاً" مع زر التحميل عبر تليجرام.
   - يدعم قيود Package Visibility لنظام Android 11+ عبر وسم `<queries>`.
4. **الحماية ومقاومة العبث (Anti-Tamper & Obfuscation)**:
   - تم تفعيل R8/ProGuard للنسخة النهائية Release.
   - التحقق من سلامة الباكدج وتوقيع الحزمة، مع الحفاظ على وضع Debug قابلاً للاختبار.
5. **روابط الدعم والمعلومات**:
   - جميع روابط المطور Author و GitHub و Report a bug موجهة لرابط التليجرام: [https://t.me/Mistar_wolfnet](https://t.me/Mistar_wolfnet).

## طريقة البناء التلقائي عبر GitHub Actions:
المستودع يحتوي على ملف البناء السحابي `.github/workflows/build.yml`. بمجرد رفع الكود إلى GitHub:
1. اذهب إلى تبويب **Actions** في مستودع GitHub الخاص بك.
2. ستجد مهام البناء بدأت تلقائياً.
3. بعد اكتمال البناء، ستجد ملف الـ APK جاهزاً للتحميل والتثبيت تحت قسم **Artifacts** باسم `Wolf-Custom-VPN-APK`.
