/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void update(Resume r) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(r.uuid)) {
                storage[i] = r;
                return;
            }
            System.out.println("ERROR");
        }
    }

    public void save(Resume r) {
        boolean notPresent = true;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(r.uuid)) {
                notPresent = false;
                break;
            }


        }
        if (notPresent) {
            storage[size] = r;
            size++;
        } else {
            System.out.println("ERROR");
        }


    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }

        }

        return null;
    }

    public void delete(String uuid) {

        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
                return;
            }
            System.out.println("ERROR");

        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumeAll = new Resume[size];
        for (int i = 0; i < size; i++) {
            resumeAll[i] = storage[i];

        }

        return resumeAll;
    }

    public int size() {
        return size;
    }
}
