# Data Schema

## Описание
Данная схема данных описывает ключевые сущности, их атрибуты, типы данных и связи. Она спроектирована на основе [исходной ER-модели](../analysis/4-design/er-model.md) и дополняет [Доменные сущности (Domain Entities)](domain-entities.md).
Модель полностью покрывает бизнес-требования проекта:
- **Управление пользователями:** хранение контактных данных, email и профиля аллергий (в виде списка строк).
- **Бронирование и оплата:** поддержка временных слотов (резерв места истекает, если не оплачен — используется статус `PENDING_PAYMENT` и поле `expiresAt`), учет количества забронированных мест и опциональная аренда инвентаря.
- **Управление расписанием и классами:** связь слотов с программами и шефами, учет свободных мест (`availableSeats`) и остатков прокатного инвентаря (`availableEquipmentStock`), хранение цен в копейках для исключения ошибок округления (`basePrice`, `equipmentTariff`).
- **Обратная связь:** возможность клиенту оценивать шефа после завершения класса (`chefRating`).

## Enums

**ClassComplexity**
- `EASY`
- `MEDIUM`
- `HARD`

**SlotStatus**
- `SCHEDULED`
- `IN_PROGRESS`
- `COMPLETED`
- `CANCELLED`

**BookingStatus**
- `PENDING_PAYMENT` (Ожидает оплаты)
- `ACTIVE` (Подтверждено и оплачено)
- `CANCELLED_BY_CLIENT` (Отменено клиентом)
- `CANCELLED_BY_STUDIO` (Отменено студией)
- `COMPLETED` (Завершено)

---

## Сущности

### 1. Client
Пользователь мобильного приложения, бронирующий классы.

| Поле | Тип | Обязательность | Описание | Ограничения |
|---|---|---|---|---|
| `id` | UUID | Non-nullable | Уникальный идентификатор | Primary Key |
| `phone` | String | Non-nullable | Номер телефона для авторизации | Unique |
| `email` | String | Nullable | Email клиента | |
| `name` | String | Nullable | Имя клиента | |
| `allergyProfile` | Array of Strings | Non-nullable | Список выбранных аллергенов | По умолчанию `[]` |

### 2. ClassProgram
Шаблон кулинарного мастер-класса.

| Поле | Тип | Обязательность | Описание | Ограничения |
|---|---|---|---|---|
| `id` | UUID | Non-nullable | Уникальный идентификатор | Primary Key |
| `title` | String | Non-nullable | Название программы | |
| `description` | String | Non-nullable | Подробное описание и состав меню | |
| `complexity` | ClassComplexity | Non-nullable | Уровень сложности | |
| `basePrice` | Integer | Non-nullable | Базовая стоимость участия (в копейках) | `>= 0` |

### 3. Chef
Кулинарный эксперт.

| Поле | Тип | Обязательность | Описание | Ограничения |
|---|---|---|---|---|
| `id` | UUID | Non-nullable | Уникальный идентификатор | Primary Key |
| `name` | String | Non-nullable | Имя или псевдоним | |
| `bio` | String | Nullable | Краткая биография | |
| `rating` | Float | Non-nullable | Средняя оценка шефа | От `0.0` до `5.0` |

### 4. Slot
Конкретное проведение класса в заданное время.

| Поле | Тип | Обязательность | Описание | Ограничения |
|---|---|---|---|---|
| `id` | UUID | Non-nullable | Уникальный идентификатор | Primary Key |
| `programId` | UUID | Non-nullable | Ссылка на программу | Foreign Key -> ClassProgram |
| `chefId` | UUID | Non-nullable | Ссылка на шефа | Foreign Key -> Chef |
| `datetimeStart`| Timestamp | Non-nullable | Время начала (ISO 8601) | |
| `duration` | Integer | Non-nullable | Продолжительность (мин) | `> 0` |
| `maxCapacity` | Integer | Non-nullable | Макс. число мест | `> 0` |
| `availableSeats`| Integer | Non-nullable | Оставшиеся места | `>= 0` |
| `status` | SlotStatus | Non-nullable | Статус слота | |
| `address` | String | Non-nullable | Адрес проведения | |
| `availableEquipmentStock`| Integer| Non-nullable| Доступный инвентарь (шт) | `>= 0` |
| `equipmentTariff`| Integer | Non-nullable | Стоимость аренды инвентаря (в копейках)| `>= 0` |

### 5. Booking
Факт записи клиента на слот.

| Поле | Тип | Обязательность | Описание | Ограничения |
|---|---|---|---|---|
| `id` | UUID | Non-nullable | Уникальный идентификатор | Primary Key |
| `idempotencyKey` | String | Non-nullable | Ключ защиты от дублей | Unique |
| `clientId` | UUID | Non-nullable | Ссылка на клиента | Foreign Key -> Client |
| `slotId` | UUID | Non-nullable | Ссылка на слот | Foreign Key -> Slot |
| `seatsCount` | Integer | Non-nullable | Количество мест | `> 0` |
| `status` | BookingStatus| Non-nullable | Текущий статус бронирования | |
| `createdAt` | Timestamp | Non-nullable | Время создания брони | Default = NOW() |
| `expiresAt` | Timestamp | Nullable | Время истечения резерва | Только для `PENDING_PAYMENT` |
| `fixedBasePrice` | Integer | Non-nullable | Замороженная базовая стоимость на момент брони (копейки) | `>= 0` |
| `needsRentalEquipment`| Boolean | Non-nullable | Признак аренды экипировки | |
| `equipmentTariff` | Integer | Non-nullable | Замороженная стоимость аренды (копейки) | `>= 0`, по умолчанию `0` |
| `chefRating` | Integer | Nullable | Оценка, выставленная шефу | От `1` до `5` |

---

## Связи (Relationships)
- **Client (1) — (M) Booking:** Один клиент может совершать несколько бронирований. `Booking` всегда принадлежит строго одному `Client`.
- **Slot (1) — (M) Booking:** На один слот может быть зарегистрировано несколько клиентов (в пределах `availableSeats`).
- **ClassProgram (1) — (M) Slot:** Одна и та же программа может реализовываться много раз как разные слоты в расписании.
- **Chef (1) — (M) Slot:** Один шеф может вести множество слотов. Один слот всегда ведется ровно одним шефом.
