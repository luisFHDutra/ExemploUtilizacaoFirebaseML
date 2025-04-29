# 📸 Firebase ML OCR Example (ML Kit)

O Firebase Machine Learning é um SDK para dispositivos móveis que leva a experiência em aprendizado de máquina do Google para apps Android e iOS em um pacote eficiente e fácil de usar.
Não importa se você é novo ou experiente em aprendizado de máquina, é possível implementar a funcionalidade necessária com apenas algumas linhas de código.
Não é preciso ter um conhecimento profundo de redes neurais ou otimização de modelos para começar.
Por outro lado, se você é um desenvolvedor de ML experiente, o Firebase ML fornece APIs convenientes que ajudam você a usar seus modelos personalizados do TensorFlow Lite nos seus apps para dispositivos móveis.

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
- Android SDK (API 24)
- Java 11
- Gradle

---

## 📦 Dependências

No arquivo `build.gradle (app)`:

```
implementation 'com.google.mlkit:text-recognition:16.0.0'
implementation 'androidx.appcompat:appcompat:1.6.1'
implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
```

No arquivo `AndroidManifest.xml` na tag `application`:

```
<meta-data
  android:name="com.google.firebase.ml.vision.DEPENDENCIES"
  android:value="ocr"/>
```

---

## 📱 Capturas de Tela
1. Selecionar uma imagem

![Image](https://github.com/user-attachments/assets/72270726-a885-4288-8811-ee57e9464d97)

2. Imagem carregada e texto extraído

![Image](https://github.com/user-attachments/assets/c6cb34fe-ea14-47ef-a594-516f955768a5)

---

## 🧠 Detalhes Técnicos

- A imagem selecionada pelo usuário é convertida em Bitmap e redimensionada para largura máxima de 1024px, garantindo melhor performance e compatibilidade com o modelo on-device do ML Kit.
- A API TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS) é utilizada para OCR, evitando conflitos com bibliotecas antigas.
- Nenhum dado é enviado à nuvem: o processamento ocorre 100% no dispositivo, oferecendo maior privacidade e uso offline.

---

## 📌 Observações

- O projeto utiliza o ML Kit na versão standalone, sem Firebase obrigatório.
- Não há necessidade de internet para executar a detecção de texto (funciona 100% offline).
- O modelo on-device é eficiente, leve e gratuito.
