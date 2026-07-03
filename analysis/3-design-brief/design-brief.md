# Реестр экранов и интерфейсных единиц (Design Brief)

В данном документе представлен верхнеуровневый реестр всех интерфейсных экранов и модальных окон/шторок (Bottom Sheets), необходимых для реализации мобильного приложения кулинарной студии.

## Основные экраны (Screens)

| Код | Название | Назначение | Трассировка (Требования) |
| :--- | :--- | :--- | :--- |
| [**SCR-001**](./SCR-001_login.md) | Экран входа | Ввод номера телефона, согласия на обработку ПД и OTP-кода. | [FR-10](../2-requirements/functional-requirements.md), [FR-20](../2-requirements/functional-requirements.md), [UC-10](../2-requirements/use-cases.md), [US-10](../2-requirements/user-stories.md), [US-30](../2-requirements/user-stories.md) |
| [**SCR-002**](./SCR-002_registration.md) | Экран регистрации | Ввод имени пользователя при первой авторизации. | [FR-15](../2-requirements/functional-requirements.md), [UC-10](../2-requirements/use-cases.md), [US-20](../2-requirements/user-stories.md) |
| [**SCR-003**](./SCR-003_schedule.md) | Расписание (Главный) | Отображение списка доступных классов с фильтром по датам. | [FR-40](../2-requirements/functional-requirements.md), [FR-45](../2-requirements/functional-requirements.md), [FR-115](../2-requirements/functional-requirements.md), [UC-20](../2-requirements/use-cases.md), [UC-70](../2-requirements/use-cases.md), [US-40](../2-requirements/user-stories.md), [US-50](../2-requirements/user-stories.md), [BR-10](../2-requirements/business-requirements.md) |
| [**SCR-004**](./SCR-004_slot_details.md) | Детали класса | Подробная информация о выбранном слоте (описание, шеф, адрес). | [FR-50](../2-requirements/functional-requirements.md), [UC-20](../2-requirements/use-cases.md), [US-70](../2-requirements/user-stories.md) |
| [**SCR-005**](./SCR-005_payment_details.md) | Инструкция по оплате | Отображение реквизитов, итоговой суммы и таймера после создания брони. | [FR-80](../2-requirements/functional-requirements.md), [UC-30](../2-requirements/use-cases.md), [US-100](../2-requirements/user-stories.md), [BR-40](../2-requirements/business-requirements.md) |
| [**SCR-006**](./SCR-006_profile_and_bookings.md) | Профиль и Мои записи | Список активных бронирований пользователя и доступ к настройкам профиля (аллергии). | [FR-30](../2-requirements/functional-requirements.md), [UC-40](../2-requirements/use-cases.md), [UC-50](../2-requirements/use-cases.md), [US-130](../2-requirements/user-stories.md), [FR-85](../2-requirements/functional-requirements.md) |
| [**SCR-007**](./SCR-007_upcoming_booking_details.md) | Детали активной брони | Памятка по предстоящему занятию, адрес, статус оплаты и аренды. Точка входа для Push-уведомлений. | [FR-110](../2-requirements/functional-requirements.md), [UC-80](../2-requirements/use-cases.md), [US-150](../2-requirements/user-stories.md), [US-160](../2-requirements/user-stories.md) |

## Всплывающие элементы (Bottom Sheets / Modals)

| Код | Название | Назначение | Трассировка (Требования) |
| :--- | :--- | :--- | :--- |
| [**BS-001**](./BS-001_booking_confirmation.md) | Оформление бронирования | Расчет стоимости слота и выбор аренды инвентаря перед подтверждением. | [FR-60](../2-requirements/functional-requirements.md), [FR-65](../2-requirements/functional-requirements.md), [FR-70](../2-requirements/functional-requirements.md), [FR-75](../2-requirements/functional-requirements.md), [UC-30](../2-requirements/use-cases.md), [US-80](../2-requirements/user-stories.md), [US-90](../2-requirements/user-stories.md), [BR-30](../2-requirements/business-requirements.md) |
| [**BS-002**](./BS-002_allergies_selection.md) | Выбор аллергий | Список тегов для отметки аллергий пользователя в профиле. | [FR-25](../2-requirements/functional-requirements.md), [UC-40](../2-requirements/use-cases.md), [US-60](../2-requirements/user-stories.md) |
| [**BS-003**](./BS-003_cancellation_modal.md) | Отмена бронирования | Подтверждение отмены записи с предупреждением о штрафах (если применимо). | [FR-85](../2-requirements/functional-requirements.md), [FR-90](../2-requirements/functional-requirements.md), [UC-50](../2-requirements/use-cases.md), [US-110](../2-requirements/user-stories.md), [US-120](../2-requirements/user-stories.md), [BR-20](../2-requirements/business-requirements.md) |
| [**BS-004**](./BS-004_chef_rating.md) | Оценка шефа | Шторка для выставления оценки (1-5 звезд) прошедшему классу. | [FR-100](../2-requirements/functional-requirements.md), [FR-105](../2-requirements/functional-requirements.md), [UC-60](../2-requirements/use-cases.md), [US-140](../2-requirements/user-stories.md), [BR-50](../2-requirements/business-requirements.md) |

---
*Примечание: Детальное описание каждого экрана представлено в соответствующих файлах в текущей директории.*
