
# 🧠 MindSpace – Personal Knowledge Journal

> A modern Android app (built in Java) to capture, organize, and reflect on your thoughts, ideas, and inspirations — all in one place.

---

## 🚀 Overview
**MindSpace** helps users store and organize their random thoughts, notes, and ideas into structured collections.  
Offline-first using **Room**, with optional **Firebase sync** for backup and multi-device access.

---

## 🧩 Core Features
- 🗂 Create & manage **Collections** (e.g. Ideas, Dreams, Learning)
- ✍️ Add, edit, delete **Thoughts** within collections
- 🔖 Tag & search thoughts
- ⭐ Mark favorites
- ☁️ Optional Firebase Cloud Backup
- 🔔 Daily reminder to add new thoughts
- 📊 Analytics dashboard (thought count, active days)
- 🌙 Dark mode support

---

## 🧭 Roadmap

### **Phase 1 – Core App (Offline)**
- [ ] Setup Android project (Java + XML)
- [ ] Implement Room Database (Collection + Thought tables)
- [ ] CRUD for Collections
- [ ] CRUD for Thoughts
- [ ] RecyclerView for lists
- [ ] Search + Filter
- [ ] UI polish (Material Design)

### **Phase 2 – Enhancements**
- [ ] Favorites & sorting
- [ ] Notifications (WorkManager)
- [ ] Stats screen (total thoughts, daily activity)
- [ ] Custom icons for collections
- [ ] Local backup (export/import JSON)

### **Phase 3 – Firebase Integration**
- [ ] Firebase Auth (optional login)
- [ ] Firestore Sync (mirror local data)
- [ ] Firebase Storage for attachments (images)
- [ ] Cloud backup toggle in settings

### **Phase 4 – Advanced**
- [ ] Mood tracker per thought
- [ ] Random thought generator
- [ ] PDF/Markdown export
- [ ] Widgets (home screen thought shortcut)
- [ ] Dark mode toggle

---

## 🗃 Database Schema (Room)

### **Collection Table**
| Field | Type | Description |
|-------|------|--------------|
| id | Int (PK) | Auto-generated |
| title | String | Collection title |
| icon | String | Optional emoji/icon |
| createdAt | Long | Timestamp |

### **Thought Table**
| Field | Type | Description |
|-------|------|--------------|
| id | Int (PK) | Auto-generated |
| collectionId | Int (FK) | Linked to Collection |
| content | String | Thought text |
| tag | String | Optional |
| favorite | Boolean | Default false |
| createdAt | Long | Timestamp |

---

## 🧠 Learning Goals
- Room DB with relationships  
- MVVM architecture (optional later)  
- RecyclerView adapters  
- Data persistence + LiveData  
- Firebase Firestore sync  
- WorkManager for notifications  
- Clean UI structure  

---

## 🛠 Tools & Libraries
| Purpose | Library |
|----------|----------|
| Local DB | Room |
| Async Tasks | LiveData / Executors |
| API / Network | Retrofit (optional) |
| Cloud | Firebase Auth + Firestore |
| Charts | MPAndroidChart (optional) |
| Notifications | WorkManager |
| UI Components | Material Components |

---

## 🎨 UI Flow

**1️⃣ Main Screen**
- Displays all Collections  
- Floating Action Button → Add Collection  

**2️⃣ Collection Screen**
- Shows all thoughts in selected collection  
- Search bar, Filter tags, Sort dropdown  
- FAB → Add new thought  

**3️⃣ Add/Edit Thought Screen**
- Text input, Tag input, Favorite toggle  
- Save → persist to DB  

**4️⃣ Analytics / Settings**
- Thought count per collection  
- Backup toggle, Dark mode, About  

---

## 💡 Future Enhancements
- AI-assisted “Summarize Thought” (via API)  
- Cross-platform sync (React Native version)  
- Voice input for quick thought capture  
- End-to-end encryption for privacy  

---

## 🧑‍💻 Author
Built by **[Light]** – Junior React Native Developer exploring native Android (Java)...
