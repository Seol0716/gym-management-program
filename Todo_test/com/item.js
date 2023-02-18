import React, {useState} from 'react';
import {
  View,
  Text,
  StyleSheet,
  Image,
  TouchableOpacity,
  Alert,
  TextInput,
  Keyboard,
  Input,
  Dimensions,
} from 'react-native';

function Item({id, text, done, editable, onToggle, onDelete, onModify, item}) {
  const [newEdit, setEdit] = useState(item.text);
  const onPress_onToggle = () => {
    onToggle(id);
  };

  const onPress_onDelete = () => {
    onDelete(id);
  };

  const onPress_onModify = () => {
    onModify(id);
  };

  const onPress_onEdit = () => {
    item.text = newEdit;
    Keyboard.dismiss();
  }

  return (
    <View style={styles.item}>
      <TouchableOpacity onPress={onPress_onToggle}>
        <View style={[styles.circle, done && styles.failed]}>
          {done && (
            <Image
              source={require('../com/toggle.png')}
              style={styles.toggle_img}
            />
          )}
        </View>
      </TouchableOpacity>

      <TextInput
        editable={editable}
        onSubmitEditing={onPress_onEdit}
        style={[styles.text, done && styles.line]}
        value={newEdit}
        onChangeText={newEdit => setEdit(newEdit)}
        autoFocus={true}
      />

      <TouchableOpacity onPress={onPress_onModify}>
        <View style={styles.img_view}>
          <Image source={require('../com/su.png')} style={styles.img} />
        </View>
      </TouchableOpacity>

      <TouchableOpacity onPress={onPress_onDelete}>
        <View style={styles.img_view}>
          <Image source={require('../com/delete.png')} style={styles.img} />
        </View>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  item: {
    flexDirection: 'row',
    backgroundColor: '#deb887',
    margin: 8,
    height: 70,
    borderRadius: 20,
    marginLeft: 20,
    marginRight: 20,
  },
  failed: {
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#deb887',
    marginBottom: 1,
  },

  circle: {
    width: 25,
    height: 25,
    borderRadius: 12,
    borderWidth: 2,
    marginTop: 23,
    marginLeft: 8,
    borderColor: '#8b4513',
  },

  text: {
    fontSize: 20,
    marginLeft: 13,
    flex: 1,
    color: '#000000',
    width: Dimensions.get('window').width - 45,
  },

  img_view: {
    width: 25,
    height: 25,
    marginRight: 13,
  },

  img: {
    width: 27,
    height: 27,
    marginTop: 19,
  },

  toggle_img: {
    alignItems: 'center',
    justifyContent: 'center',
    width: 25,
    height: 25,
  },

  line: {
    color: '#9e9e9e',
    textDecorationLine: 'line-through',
  }
});

export default Item;