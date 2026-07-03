# Нефункциональные требования (Non-Functional Requirements)

| ID | Формулировка | Приоритет | Источник |
| :--- | :--- | :--- | :--- |
| NFR-10 | Клиентское приложение реализуется как мобильное приложение (iOS/Android), интегрируемое с существующим REST API бэкенда. | Must have | [domain_model.md](../1-elicitation/domain_model.md), Введение |
| NFR-20 | Вся бизнес-логика (расчет рейтингов, статусов слотов, таймаутов на оплату) и атомарные проверки (защита от двойных бронирований) осуществляются на стороне бэкенда. Приложение выступает тонким клиентом. | Must have | [domain_model.md](../1-elicitation/domain_model.md), Раздел 3 (Бронирование) |
| NFR-30 | В качестве единственного канала информирования пользователей (напоминания, уведомления об отменах) используются Push-уведомления (без резервирования через SMS). | Must have | [questions_to_customer.md](../1-elicitation/questions_to_customer.md) (Ответ 9), [requirements_summary.md](../1-elicitation/requirements_summary.md) |
| NFR-40 | В MVP-версии приложения не реализуются элементы программы лояльности (бейджи, счетчики) и онлайн-эквайринг. | Won't have | [requirements_summary.md](../1-elicitation/requirements_summary.md), Раздел 2, 3 |
