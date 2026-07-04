# Логика Оценки шефа (BS-004)

**ID:** BS-004_LOGIC  
**Тип:** Логика Bottom Sheet  
**Домен:** 04. Бронирование  
**Приоритет:** Low

---

## Обзор

Логика компонента оценки (Stars Rating) и отправки результата на бэкенд.

---

## Флоу

```mermaid
flowchart TD
    Start([Открытие с booking_id]) --> Rating[Юзер выбирает 1-5 звезд]
    
    Rating --> EnableBtn[Кнопка активна]
    EnableBtn --> TapSubmit[Tap: Оценить]
    
    TapSubmit --> APIReq[API: rateChef]
    APIReq --> Check{Успех?}
    
    Check -->|Да| ShowToast[Toast 'Спасибо за оценку!'] --> Close([Закрыть BS])
    Check -->|Нет| ShowError[Снекбар ошибки]
```

---

## API запросы

### POST /bookings/{id}/rating (`rateChef`)

**Параметры/Body:**
| Параметр | Тип | Значение |
|----------|-----|----------|
| `rating` | Int | 1..5 |

**Обработка ответа:**
Успешный 200 ответ завершает флоу оценки.

## Связанные требования
- **FR-100** Оценка шефа (Low Priority)
