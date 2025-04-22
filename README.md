# 📸 Firebase ML OCR Example (ML Kit)

Este projeto demonstra o uso da biblioteca **ML Kit** (by Google) para realizar **detecção de texto (OCR)** em imagens selecionadas pelo usuário no Android, utilizando **Java** e **Android Studio**.

---

## 🚀 Funcionalidades

- Seleção de imagem da galeria
- Detecção de texto utilizando **ML Kit on-device**
- Exibição do texto reconhecido na tela
- Compatível com Android 7.0 (API 24) ou superior
- Implementado em **Java**

---

## 🧰 Tecnologias Utilizadas

- [ML Kit - Text Recognition](https://developers.google.com/ml-kit/vision/text-recognition/android)
- Android SDK (API 33)
- Java 11
- Gradle

---

## 📦 Dependências

No arquivo `build.gradle (app)`:

```gradle
implementation 'com.google.mlkit:text-recognition:16.0.0'
implementation 'androidx.appcompat:appcompat:1.6.1'
implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
